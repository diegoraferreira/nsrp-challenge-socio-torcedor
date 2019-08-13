package com.nsrp.challenge.service.jms;

import com.nsrp.challenge.model.ClienteModel;
import com.nsrp.challenge.service.AssociacaoClienteCampanhaService;
import com.nsrp.challenge.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateClienteConsumer {

    @Autowired
    private AssociacaoClienteCampanhaService service;

    @Autowired
    private ClienteService clienteService;

    @JmsListener(destination = "${nsrp.cliente.campanha.update.queue.name}", containerFactory = "myFactory")
    public void receiveMessage(ClienteModel clienteModel) {
        this.clienteService.finalizarCadastroDoCliente(clienteModel);
        this.service.associarAtualizacaoClienteCampanha(clienteModel);
    }
}