package com.nsrp.challenge.service;

import com.nsrp.challenge.model.CampanhaModel;
import com.nsrp.challenge.model.ClienteModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AssociacaoClienteCampanhaServiceTest {

    @Mock
    private CampanhaService campanhaService;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private AssociacaoClienteCampanhaService service;

    @Test
    public void associarClienteCampanhaNovoCliente() {
        ClienteModel clienteModel = Mockito.spy(ClienteModel.class);
        CampanhaModel campanhaModel = Mockito.mock(CampanhaModel.class);
        Long campanhaId = 1L;
        Mockito.when(campanhaModel.getId()).thenReturn(campanhaId);
        List<CampanhaModel> campanhas = Arrays.asList(campanhaModel);
        Mockito.when(campanhaService.findCampanhasByTimeDoCoracao(clienteModel.getTimeDoCoracao())).thenReturn(campanhas);

        service.associarClienteCampanha(clienteModel);

        Mockito.verify(clienteService, Mockito.times(1)).atualizarCampanhasTimeDoCoracao(clienteModel);
        Assert.assertFalse(clienteModel.getCampanhas().isEmpty());
        Assert.assertEquals(clienteModel.getCampanhas().size(), 1);

        Long value = clienteModel.getCampanhas().get(0);
        Assert.assertEquals(value, campanhaId);
    }
}