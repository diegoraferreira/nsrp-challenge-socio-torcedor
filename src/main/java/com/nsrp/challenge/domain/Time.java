package com.nsrp.challenge.domain;

import javax.persistence.*;

@Entity
@Table(name = "TIME", indexes = @Index(name = "IX_NOME", columnList = "NOME", unique = true))
@SequenceGenerator(name = "SEQ_TIME", sequenceName = "SEQ_TIME")
public class Time {

    private Long id;

    private String nome;

    public Time() {
        super();
    }

    public Time(String nome) {
        this.nome = nome;
    }

    @Id
    @GeneratedValue(generator = "SEQ_TIME", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOME", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}