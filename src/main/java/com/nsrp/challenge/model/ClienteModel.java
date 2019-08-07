package com.nsrp.challenge.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel(description = "Cliente")
public class ClienteModel {

    @ApiModelProperty(value = "Código do cliente")
    private Long id;

    @ApiModelProperty(value = "Nome completo")
    private String nomeCompleto;

    @ApiModelProperty(value = "Email")
    private String email;

    @ApiModelProperty(value = "Data de nascimento")
    private LocalDate dataNascimento;

    @ApiModelProperty(value = "Time do coração")
    private String timeDoCoracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTimeDoCoracao() {
        return timeDoCoracao;
    }

    public void setTimeDoCoracao(String timeDoCoracao) {
        this.timeDoCoracao = timeDoCoracao;
    }
}