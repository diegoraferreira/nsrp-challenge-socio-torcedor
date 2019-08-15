package com.nsrp.challenge.controller;

import com.nsrp.challenge.exceptionhandler.ApiError;
import com.nsrp.challenge.model.ClienteModel;
import com.nsrp.challenge.service.CampanhaService;
import com.nsrp.challenge.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api(tags = "API para cadastro de cliente", value = "Disponibiliza acesso para cadastro do cliente.")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CampanhaService campanhaService;

    @ApiOperation(value = "Cadastro de cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno do servidor ao cadastrar o cliente", response = ApiError.class),
    })
    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClienteModel> create(@RequestBody ClienteModel clienteModel) {
        ClienteModel clienteSalvo = this.clienteService.save(clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @ApiOperation(value = "Atualização de cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno do servidor ao atualizar o cliente", response = ApiError.class),
    })
    @PutMapping(produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClienteModel> update(@RequestBody ClienteModel clienteModel) {
        ClienteModel clienteSalvo = this.clienteService.update(clienteModel);
        return ResponseEntity.status(HttpStatus.OK).body(clienteSalvo);
    }

    @ApiOperation(value = "Busca de cliente por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado", response = ApiError.class),
            @ApiResponse(code = 500, message = "Erro interno do servidor ao buscar o cliente", response = ApiError.class),
    })
    @GetMapping(value = "/list/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClienteModel> findByClienteId(@PathVariable("id") Long id) {
        ClienteModel clientModel = this.clienteService.findByClienteId(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientModel);
    }

    @ApiOperation(value = "Lista todos os clientes disponiveis")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno do servidor ao buscar os clientes", response = ApiError.class),
    })
    @GetMapping(value = "/list", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ClienteModel>> findAll() {
        List<ClienteModel> clientModel = this.clienteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientModel);
    }
}