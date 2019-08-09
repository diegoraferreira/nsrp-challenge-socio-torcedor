package com.nsrp.challenge.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Time")
public class TimeModel {

    private Long id;

    private String nome;

    public TimeModel() {
    }

    public TimeModel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @ApiModelProperty(value = "Código do time")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty(value = "Nome do time")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}