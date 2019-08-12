package com.nsrp.challenge.service;

import com.nsrp.challenge.model.ClienteModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AssociacaoClienteCampanhaConsumer {

    private static final String QUEUE_NAME = "AssociacaoClienteCampanhaQueue";

    @Value("nsrp.associacao.cliente.campanha.queue.name")
    private String queueName;

    @JmsListener(destination = QUEUE_NAME, containerFactory = "myFactory")
    public void receiveMessage(ClienteModel clienteModel) {
        System.out.println("bla");
    }
}