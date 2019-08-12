package com.nsrp.challenge.service;

import com.nsrp.challenge.model.ClienteModel;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AssociacaoClienteCampanhaConsumer {

    @JmsListener(destination = "${nsrp.associacao.cliente.campanha.queue.name}", containerFactory = "myFactory")
    public void receiveMessage(ClienteModel clienteModel) {
        System.out.println(clienteModel);
    }
}