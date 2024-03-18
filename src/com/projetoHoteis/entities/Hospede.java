package com.projetoHoteis.entities;

public class Hospede {
    private String nome;
    private String cpf;
    private Integer id;

    public Hospede(String nome, String cpf, Integer id) {
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getId() {
        return id;
    }
}
