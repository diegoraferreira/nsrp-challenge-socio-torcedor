package com.nsrp.challenge.service;

import com.nsrp.challenge.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CreateClienteConsumer {

    @Autowired
    private AssociacaoClienteCampanhaService service;

    @Autowired
    private ClienteService clienteService;

    @JmsListener(destination = "${nsrp.cliente.campanha.create.queue.name}", containerFactory = "myFactory")
    public void receiveMessage(ClienteModel clienteModel) {
        this.clienteService.finalizarCadastroDoCliente(clienteModel);
        this.service.associarClienteCampanha(clienteModel);
    }
}