package com.barber.barber.model.Agendamento;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AgendamentoDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirAgendamento(Agendamento agendamento){
        String sql = "INSERT INTO agendamento(cliente, data, horario, servico) VALUES(?,?,?,?)";
        Object[] parametros = new Object[4];
        parametros[0] = agendamento.getCliente();
        parametros[1] = agendamento.getData();
        parametros[2] = agendamento.getHorario();
        parametros[3] = agendamento.getServico();
        jdbc.update(sql,parametros);
    }

    public List<Agendamento> listarAgendamentos(){
        String sql = "SELECT * FROM agendamento";
        List<Agendamento> agendamentos = jdbc.query(sql, (result, index)->{
            Agendamento agendamento = new Agendamento();
            agendamento.setId(result.getInt("id"));
            agendamento.setCliente(result.getString("cliente"));
            agendamento.setData(result.getString("data"));
            agendamento.setHorario(result.getString("horario"));
            agendamento.setServico(result.getString("servico"));
            return agendamento;
        });
        return agendamentos;
    }

    public List<Agendamento> listarAgendamentosPorCliente(String cliente){
        String sql = "SELECT * FROM agendamento WHERE agendamento.cliente = ?";
        List<Agendamento> agendamentos = jdbc.query(sql, new Object[]{cliente}, (result, index)->{
            Agendamento agendamento = new Agendamento();
            agendamento.setId(result.getInt("id"));
            agendamento.setCliente(result.getString("cliente"));
            agendamento.setData(result.getString("data"));
            agendamento.setHorario(result.getString("horario"));
            agendamento.setServico(result.getString("servico"));
            return agendamento;
        });
        return agendamentos;
    }
}
