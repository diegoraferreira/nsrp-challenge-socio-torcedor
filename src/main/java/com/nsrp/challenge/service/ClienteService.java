package com.nsrp.challenge.service;

import com.nsrp.challenge.domain.Cliente;
import com.nsrp.challenge.model.ClienteModel;
import com.nsrp.challenge.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private TimeService timeService;

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private AssociacaoClienteCampanhaProducer producer;

    public void save(ClienteModel clienteModel) {
        Cliente cliente = new Cliente();
        cliente.setNomeCompleto(clienteModel.getNomeCompleto());
        cliente.setEmail(clienteModel.getEmail());
        cliente.setDataNascimento(clienteModel.getDataNascimento());
        cliente.setTimeDoCoracaoId(timeService.findByNome(clienteModel.getTimeDoCoracao()).getId());

        repository.save(cliente);
        producer.sendMenssage(clienteModel);
    }
}