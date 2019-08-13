package com.nsrp.challenge.service.jms;

import com.nsrp.challenge.model.ClienteModel;
import com.nsrp.challenge.service.AssociacaoClienteCampanhaService;
import com.nsrp.challenge.service.ClienteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UpdateClienteConsumerTest {

    @Mock
    private AssociacaoClienteCampanhaService service;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private UpdateClienteConsumer consumer;

    @Test
    public void receiveMessage() {
        ClienteModel clienteModel = Mockito.mock(ClienteModel.class);

        consumer.receiveMessage(clienteModel);

        Mockito.verify(clienteService, Mockito.times(1)).finalizarCadastroDoCliente(clienteModel);
        Mockito.verify(service, Mockito.times(1)).associarAtualizacaoClienteCampanha(clienteModel);
    }
}