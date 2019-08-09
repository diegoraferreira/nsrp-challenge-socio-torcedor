package com.nsrp.challenge.service;

import com.nsrp.challenge.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class AssociacaoClienteCampanhaProducer {

    @Value("nsrp.associacao.cliente.campanha.queue.name")
    private String queueName;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMenssage(ClienteModel cliente) {
        jmsTemplate.convertAndSend(queueName, cliente);
    }
}