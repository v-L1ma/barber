package com.barber.barber.infra.web.DTOs;

import java.time.LocalDate;

public class CadastrarClienteDto {
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String celular;
    private String senha;
    private String confirmarSenha;

    public CadastrarClienteDto(String nome, LocalDate dataNascimento, String email, String celular, String senha, String confirmarSenha) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }
}
