package com.nsrp.challenge.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENTE", indexes = @Index(name = "IX_EMAIL", columnList = "EMAIL", unique = true))
@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE")
public class Cliente {

    private Long id;

    private String nomeCompleto;

    private String email;

    private LocalDate dataNascimento;

    private Long timeDoCoracaoId;

    private List<Long> campanhas = new ArrayList<>();

    @Id
    @GeneratedValue(generator = "SEQ_CLIENTE", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOME_COMPLETO")
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    @Column(name = "EMAIL", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Column(name = "ID_TIME", nullable = false)
    public Long getTimeDoCoracaoId() {
        return timeDoCoracaoId;
    }

    public void setTimeDoCoracaoId(Long timeDoCoracaoId) {
        this.timeDoCoracaoId = timeDoCoracaoId;
    }

    @ElementCollection
    @CollectionTable(name = "CLIENTE_CAMPANHA", joinColumns = @JoinColumn(name = "ID_CLIENTE", nullable = false))
    public List<Long> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Long> campanhas) {
        this.campanhas = campanhas;
    }
}
