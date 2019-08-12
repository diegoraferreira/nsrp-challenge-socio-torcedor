package com.nsrp.challenge.service;

import com.nsrp.challenge.domain.Cliente;
import com.nsrp.challenge.model.ClienteModel;
import com.nsrp.challenge.repository.ClienteRepository;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    private static final String CREATE_QUEUE_NAME = "queue1";

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteProducer producer;

    @InjectMocks
    private ClienteService service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Captor
    private ArgumentCaptor<Cliente> captor;

    @Before
    public void before() {
        ReflectionTestUtils.setField(service, "createQueueName", CREATE_QUEUE_NAME);
    }

    @Test
    public void saveClienteNaoExistente() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNomeCompleto("CLIENTE");
        clienteModel.setEmail("email@clienteModel.com.br");
        clienteModel.setDataNascimento(LocalDate.of(1986, 6, 12));

        service.save(clienteModel);

        Mockito.verify(producer, Mockito.times(1)).sendMenssage(CREATE_QUEUE_NAME, clienteModel);
        Mockito.verify(repository, Mockito.times(1)).save(captor.capture());
        Cliente cliente = captor.getValue();

        Assert.assertNotNull(cliente);
        Assert.assertEquals(clienteModel.getNomeCompleto(), cliente.getNomeCompleto());
        Assert.assertEquals(clienteModel.getEmail(), cliente.getEmail());
        Assert.assertEquals(clienteModel.getDataNascimento(), cliente.getDataNascimento());
    }

    @Test
    public void saveClienteExistenteRetornaErro() {
        ClienteModel clienteModel = Mockito.mock(ClienteModel.class);
        Mockito.when(clienteModel.getEmail()).thenReturn("clienteModel@teste.com.br");
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(Mockito.mock(Cliente.class)));

        expectedException.expect(EntityExistsException.class);
        expectedException.expectMessage("Já existe um cadastro para o cliente com email clienteModel@teste.com.br");
        service.save(clienteModel);
    }
}