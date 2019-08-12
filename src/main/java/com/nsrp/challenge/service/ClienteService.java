package com.nsrp.challenge.service;

import com.nsrp.challenge.domain.Cliente;
import com.nsrp.challenge.model.ClienteModel;
import com.nsrp.challenge.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private TimeService timeService;

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private ClienteProducer producer;

    @Value("${nsrp.cliente.campanha.create.queue.name}")
    private String createQueueName;

    @Transactional
    public ClienteModel save(ClienteModel clienteModel) {
        Optional<Cliente> clienteOptional = this.repository.findByEmail(clienteModel.getEmail());
        if (clienteOptional.isPresent()) {
            String message = "Já existe um cadastro para o cliente com email %s";
            throw new EntityExistsException(String.format(message, clienteModel.getEmail()));
        }

        Cliente cliente = new Cliente();
        cliente.setNomeCompleto(clienteModel.getNomeCompleto());
        cliente.setEmail(clienteModel.getEmail());
        cliente.setDataNascimento(clienteModel.getDataNascimento());
        repository.save(cliente);

        clienteModel.setId(cliente.getId());
        this.producer.sendMenssage(createQueueName, clienteModel);
        return clienteModel;
    }

    public void atualizarCampanhasTimeDoCoracao(ClienteModel clienteModel) {
        Optional<Cliente> clienteOptional = this.repository.findByEmail(clienteModel.getEmail());
        if (!clienteOptional.isPresent()) {
            String message = "Cliente %s não encontrado";
            throw new EntityNotFoundException(String.format(message, clienteModel.getEmail()));
        }

        Cliente cliente = clienteOptional.get();
        cliente.getCampanhas().clear();
        cliente.setCampanhas(clienteModel.getCampanhas());
        repository.save(cliente);
    }

    public void finalizarCadastroDoCliente(ClienteModel clienteModel) {
        Optional<Cliente> clienteOptional = this.repository.findByEmail(clienteModel.getEmail());
        if (!clienteOptional.isPresent()) {
            String message = "Cliente %s não encontrado";
            throw new EntityNotFoundException(String.format(message, clienteModel.getEmail()));
        }

        Cliente cliente = clienteOptional.get();
        cliente.setTimeDoCoracaoId(timeService.findByNome(clienteModel.getTimeDoCoracao()).getId());
        this.repository.save(cliente);
    }

    public Optional<ClienteModel> findByEmail(String email) {
        Optional<Cliente> clienteOptional = this.repository.findByEmail(email);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            ClienteModel clienteModel = new ClienteModel();
            clienteModel.setId(cliente.getId());
            clienteModel.setNomeCompleto(cliente.getNomeCompleto());
            clienteModel.setEmail(cliente.getEmail());
            clienteModel.setDataNascimento(cliente.getDataNascimento());
            clienteModel.setTimeDoCoracaoId(cliente.getTimeDoCoracaoId());
            clienteModel.setCampanhas(cliente.getCampanhas());
            return Optional.of(clienteModel);
        } else {
            return Optional.empty();
        }
    }
}