package com.nsrp.challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Collection;

@ApiModel(description = "Cliente")
public class ClienteModel {

    @ApiModelProperty(value = "Código do cliente")
    private Long id;

    @ApiModelProperty(value = "Nome completo")
    private String nomeCompleto;

    @ApiModelProperty(value = "Email")
    private String email;

    @ApiModelProperty(value = "Data de nascimento")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataNascimento;

    @ApiModelProperty(value = "Time do coração")
    private String timeDoCoracao;

    @ApiModelProperty(value = "Código Time do coração")
    private Long timeDoCoracaoId;

    @ApiModelProperty(value = "Código das campanhas associadas ao cliente")
    private Collection<Long> campanhas;

    public ClienteModel() {
        super();
    }

    public ClienteModel(Long id, String nomeCompleto, String email, LocalDate dataNascimento, Long timeDoCoracaoId, Collection<Long> campanhas) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.timeDoCoracaoId = timeDoCoracaoId;
        this.campanhas = campanhas;
    }

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

    public Long getTimeDoCoracaoId() {
        return timeDoCoracaoId;
    }

    public void setTimeDoCoracaoId(Long timeDoCoracaoId) {
        this.timeDoCoracaoId = timeDoCoracaoId;
    }

    public Collection<Long> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(Collection<Long> campanhas) {
        this.campanhas = campanhas;
    }
}