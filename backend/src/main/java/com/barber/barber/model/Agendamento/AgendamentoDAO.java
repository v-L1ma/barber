package com.barber.barber.model.Agendamento;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AgendamentoDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirAgendamento(CadastrarAgendamentoDto dto){
        String sql = "INSERT INTO agendamento(cliente, data, horario, servico) VALUES(?,?,?,?)";
        Object[] parametros = new Object[4];
        parametros[0] = dto.cliente();
        parametros[1] = dto.data();
        parametros[2] = dto.horario();
        parametros[3] = dto.servico();
        jdbc.update(sql,parametros);
    }

    public List<Map<String,Object>> listarAgendamentos(){
        String sql = "SELECT * FROM agendamento";
        return jdbc.queryForList(sql);
    }

    public List<Map<String,Object>> listarAgendamentoPorData(String data){
        String sql = "SELECT * FROM agendamento WHERE agendamento.data = ?";
        return jdbc.queryForList(sql);
    }

    public Map<String,Object> listarAgendamentoPorId(int id){
        String sql = "SELECT * FROM agendamento where agendamento.id = ?";
        return jdbc.queryForMap(sql, id);
    }

    public void atualizarAgendamento(int id, Agendamento novo){
        String sql = "UPDATE agendamento SET cliente = ?, data = ?, horario = ?, servico = ? WHERE id = ?";
        Object[] parametros = new Object[5];
        parametros[0] = novo.getCliente();
        parametros[1] = novo.getData();
        parametros[2] = novo.getHorario();
        parametros[3] = novo.getServico();
        parametros[4] = id;
        jdbc.update(sql, parametros);
    }

    public void deletarAgendamento(int id){
        String sql = "DELETE FROM agendamento WHERE id = ?";
        jdbc.update(sql, id);
    }

//    public List<Agendamento> listarAgendamentosPorCliente(String cliente){
//        String sql = "SELECT * FROM agendamento WHERE agendamento.cliente = ?";
//        List<Agendamento> agendamentos = jdbc.query(sql, new Object[]{cliente}, (result, index)->{
//            Agendamento agendamento = new Agendamento();
//            agendamento.setId(result.getInt("id"));
//            agendamento.setCliente(result.getString("cliente"));
//            agendamento.setData(result.getString("data"));
//            agendamento.setHorario(result.getString("horario"));
//            agendamento.setServico(result.getString("servico"));
//            return agendamento;
//        });
//        return agendamentos;
//    }
}
