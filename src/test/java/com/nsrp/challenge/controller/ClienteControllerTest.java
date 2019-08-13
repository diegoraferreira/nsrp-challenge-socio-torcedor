package com.nsrp.challenge.controller;

import com.nsrp.challenge.exceptionhandler.RestResponseEntityExceptionHandler;
import com.nsrp.challenge.model.ClienteModel;
import com.nsrp.challenge.repository.ClienteRepository;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteRepository repository;

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

    @Test
    public void update() throws Exception {
        String json = "{\"id\": \"1\"," +
                "\"nomeCompleto\":\"Cliente 1\", " +
                "\"email\":\"cliente1@gmail.com\", " +
                "\"dataNascimento\":\"1980-08-01\", " +
                "\"timeDoCoracao\": \"Time 1\"}";

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(1L);
        clienteModel.setNomeCompleto("Cliente 1");
        clienteModel.setEmail("cliente1@gmail.com");
        clienteModel.setDataNascimento(LocalDate.of(1980, 8, 1));
        clienteModel.setTimeDoCoracao("Time 1");
        Mockito.when(clienteService.update(Mockito.any(ClienteModel.class))).thenReturn(clienteModel);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/cliente")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nomeCompleto").value("Cliente 1"))
                .andExpect(jsonPath("$.email").value("cliente1@gmail.com"))
                .andExpect(jsonPath("$.dataNascimento[0]").value("1980"))
                .andExpect(jsonPath("$.dataNascimento[1]").value("8"))
                .andExpect(jsonPath("$.dataNascimento[2]").value("1"))
                .andExpect(jsonPath("$.timeDoCoracao").value("Time 1"));
    }

    @Test
    public void findById() throws Exception {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(1L);
        clienteModel.setNomeCompleto("NOME");
        clienteModel.setEmail("cliente@cliente.com.br");
        clienteModel.setDataNascimento(LocalDate.of(1986, 6, 12));
        clienteModel.setTimeDoCoracao("time");
        clienteModel.setTimeDoCoracaoId(1L);

        Mockito.when(clienteService.findByClienteId(Mockito.anyLong())).thenReturn(clienteModel);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/cliente/list/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nomeCompleto").value("NOME"))
                .andExpect(jsonPath("$.email").value("cliente@cliente.com.br"))
                .andExpect(jsonPath("$.dataNascimento[0]").value("1986"))
                .andExpect(jsonPath("$.dataNascimento[1]").value("6"))
                .andExpect(jsonPath("$.dataNascimento[2]").value("12"))
                .andExpect(jsonPath("$.timeDoCoracao").value("time"))
                .andExpect(jsonPath("$.timeDoCoracaoId").value("1"));
    }
}