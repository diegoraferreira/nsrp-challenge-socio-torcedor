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

    private String email;

    private LocalDate dataNascimento;

    private Time timeDoCoracao;

    private List<Campanha> campanhas = new ArrayList<>();

    @Id
    @GeneratedValue(generator = "SEQ_CLIENTE", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIME", nullable = false, foreignKey = @ForeignKey(name = "FK_TIME_CLIENTE"))
    public Time getTimeDoCoracao() {
        return timeDoCoracao;
    }

    public void setTimeDoCoracao(Time timeDoCoracao) {
        this.timeDoCoracao = timeDoCoracao;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "CLIENTE_CAMPANHA",
            joinColumns = @JoinColumn(name = "ID_CLIENTE", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "ID_CAMPANHA", nullable = false, updatable = false))
    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }
}
