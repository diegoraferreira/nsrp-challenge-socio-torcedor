package com.nsrp.challenge.controller;

import com.nsrp.challenge.exceptionhandler.RestResponseEntityExceptionHandler;
import com.nsrp.challenge.model.CampanhaModel;
import com.nsrp.challenge.model.ClienteModel;
import com.nsrp.challenge.service.ClienteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @InjectMocks
    private RestResponseEntityExceptionHandler exceptionHandler;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).setControllerAdvice(exceptionHandler).build();
    }

    @Test
    public void create() throws Exception {
        String json = "{\"nomeCompleto\":\"Cliente 1\", " +
                "\"email\":\"cliente1@gmail.com\", " +
                "\"dataNascimento\":\"1980-08-01\", " +
                "\"timeDoCoracao\": \"Time do coração\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isCreated());
    }
}