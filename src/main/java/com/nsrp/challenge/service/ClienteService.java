package com.nsrp.challenge.service;

import com.nsrp.challenge.domain.Cliente;
import com.nsrp.challenge.model.ClienteModel;
import com.nsrp.challenge.repository.ClienteRepository;
import com.nsrp.challenge.service.jms.ClienteProducer;
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

    @Value("${nsrp.cliente.campanha.update.queue.name}")
    private String updateQueueName;

    @Transactional
    public ClienteModel save(ClienteModel clienteModel) {
        Optional<Cliente> clienteOptional = this.repository.findByEmail(clienteModel.getEmail());
        if (clienteOptional.isPresent()) {
            String message = "Já existe um cadastro para o cliente com email '%s'";
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

    public ClienteModel update(ClienteModel clienteModel) {
        Optional<Cliente> clienteOptional = this.repository.findByEmail(clienteModel.getEmail());
        if (!clienteOptional.isPresent()) {
            String message = "Cliente com email '%s' não encontrado";
            throw new EntityNotFoundException(String.format(message, clienteModel.getEmail()));
        }

        Cliente cliente = clienteOptional.get();
        cliente.setNomeCompleto(clienteModel.getNomeCompleto());
        cliente.setEmail(clienteModel.getEmail());
        cliente.setDataNascimento(clienteModel.getDataNascimento());
        repository.save(cliente);

        this.producer.sendMenssage(updateQueueName, clienteModel);
        return clienteModel;
    }

    @Transactional(readOnly = true)
    public ClienteModel findByClienteId(Long id) {
        Optional<Cliente> optionalClienteModel = this.repository.findById(id);
        if (!optionalClienteModel.isPresent()) {
            String message = "Cliente com id %s não encontrado";
            throw new EntityNotFoundException(String.format(message));
        }

        Cliente cliente = optionalClienteModel.get();
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(cliente.getId());
        clienteModel.setEmail(cliente.getEmail());
        clienteModel.setNomeCompleto(cliente.getNomeCompleto());
        clienteModel.setDataNascimento(cliente.getDataNascimento());
        clienteModel.setTimeDoCoracaoId(cliente.getTimeDoCoracaoId());
        clienteModel.setTimeDoCoracao(timeService.findById(cliente.getTimeDoCoracaoId()).getNome());
        clienteModel.setCampanhas(cliente.getCampanhas());

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
            String message = "Cliente com email '%s' não encontrado";
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