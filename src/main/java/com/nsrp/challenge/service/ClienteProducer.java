package com.nsrp.challenge.service;

import com.nsrp.challenge.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClienteProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMenssage(String queue, ClienteModel cliente) {
        jmsTemplate.convertAndSend(queue, cliente);
    }
}