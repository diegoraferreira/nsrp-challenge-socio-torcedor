package com.nsrp.challenge.exceptionhandler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Model responsável por encapsular os erros da aplicação")
public class ApiError {

    @ApiModelProperty(value = "Mensagem de erro tratada ou não pela aplicação")
    private String message;

    public ApiError() {
        // serialization
    }

    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}