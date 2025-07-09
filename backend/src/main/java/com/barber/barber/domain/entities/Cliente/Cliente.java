package com.barber.barber.domain.entities.Cliente;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cliente {

    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String celular;
    private String senha;

    public Cliente(int id, String nome, LocalDate dataNascimento, String email, String celular, String senha) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static Cliente converter(Map<String,Object> registro){
        int id = (Integer) registro.get("id");

        String nome = (String) registro.get("nome");

        Date dataNascimentoSql = (Date) registro.get("dataNascimento");
        LocalDate dataNascimento = dataNascimentoSql.toLocalDate();

        String email = (String) registro.get("email");

        String celular = (String) registro.get("celular");

        String senha = (String) registro.get("senha");

        return new Cliente(id, nome, dataNascimento, email, celular, senha);
    }

    public static List<Cliente> converterVarios(List<Map<String,Object>> registro){
        ArrayList<Cliente> lista = new ArrayList<>();
        for(Map<String,Object> reg : registro){
            lista.add(converter(reg));
        }
        return lista;
    }

}
