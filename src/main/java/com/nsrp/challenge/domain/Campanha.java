package com.nsrp.challenge.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CAMPANHA")
@SequenceGenerator(name = "SEQ_CAMPANHA", sequenceName = "SEQ_CAMPANHA")
public class Campanha {

    private Long id;

    private String nome;

    private Time timeDoCoracao;

    private LocalDate dataInicioVigencia;

    private LocalDate dataFimVigencia;

    private boolean ativa;

    @Id
    @GeneratedValue(generator = "SEQ_CAMPANHA", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOME", nullable = false, unique = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIME", nullable = false, foreignKey = @ForeignKey(name = "FK_TIME_CAMPANHA"))
    public Time getTimeDoCoracao() {
        return timeDoCoracao;
    }

    public void setTimeDoCoracao(Time timeDoCoracao) {
        this.timeDoCoracao = timeDoCoracao;
    }

    @Column(name = "DATA_INICIO_VIGENCIA", nullable = false)
    public LocalDate getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    @Column(name = "DATA_FIM_VIGENCIA", nullable = false)
    public LocalDate getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDate dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    @Column(name = "ATIVA", nullable = false)
    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}