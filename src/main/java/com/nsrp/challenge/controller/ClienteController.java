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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api(tags = "API para cadastro de cliente", value = "Disponibiliza acesso para cadastro do cliente.")
@RestController("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CampanhaService campanhaService;

    @ApiOperation(value = "Cadastro de cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente cadastrada com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno do servidor ao cadastrar o cliente", response = ApiError.class),
    })
    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClienteModel> create(@RequestBody ClienteModel clienteModel) {
        ClienteModel clienteSalvo = this.clienteService.save(clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }
}