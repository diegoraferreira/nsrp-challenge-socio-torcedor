package com.nsrp.challenge.service;

import com.nsrp.challenge.model.CampanhaModel;
import com.nsrp.challenge.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociacaoClienteCampanhaService {

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private ClienteService clienteService;

    public void associarClienteCampanha(ClienteModel clienteModel) {
        List<CampanhaModel> campanhasAtivas = campanhaService.findCampanhasByTimeDoCoracao(clienteModel.getTimeDoCoracao());
        if (!campanhasAtivas.isEmpty()) {
            clienteModel.setCampanhas(campanhasAtivas.stream().map(c -> c.getId()).collect(Collectors.toList()));
            clienteService.atualizarCampanhasTimeDoCoracao(clienteModel);
        }
    }
}