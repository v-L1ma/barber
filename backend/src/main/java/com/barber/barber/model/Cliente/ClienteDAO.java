package com.barber.barber.model.Cliente;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ClienteDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirCliente(Cliente cli){
        String sql = "INSERT INTO cliente(nome, cpf) VALUES(?,?)";
        Object[] parametros = new Object[2];
        parametros[0] = cli.getNome();
        parametros[1] = cli.getCpf();
        jdbc.update(sql,parametros);
    }

}
