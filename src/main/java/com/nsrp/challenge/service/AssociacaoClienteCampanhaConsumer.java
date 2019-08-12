package com.nsrp.challenge.service;

import com.nsrp.challenge.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AssociacaoClienteCampanhaConsumer {

    @Autowired
    private AssociacaoClienteCampanhaService service;

    @JmsListener(destination = "${nsrp.associacao.cliente.campanha.queue.name}", containerFactory = "myFactory")
    public void receiveMessage(ClienteModel clienteModel) {
        this.service.associarClienteCampanha(clienteModel);
    }
}