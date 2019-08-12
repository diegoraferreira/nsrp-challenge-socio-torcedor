package com.nsrp.challenge.service;

import com.nsrp.challenge.model.ClienteModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class AssociacaoClienteCampanhaProducerTest {

    private static final String QUEUE_NAME = "queueTest";

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private AssociacaoClienteCampanhaProducer producer;

    @Before
    public void init() {
        ReflectionTestUtils.setField(producer, "queueName", QUEUE_NAME);
    }

    @Test
    public void sendMenssage() {
        ClienteModel cliente = Mockito.mock(ClienteModel.class);
        producer.sendMenssage(cliente);
        Mockito.verify(jmsTemplate, Mockito.times(1)).convertAndSend(QUEUE_NAME, cliente);
    }
}