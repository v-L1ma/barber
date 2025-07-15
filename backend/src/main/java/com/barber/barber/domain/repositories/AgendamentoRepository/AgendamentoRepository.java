package com.barber.barber.domain.repositories.AgendamentoRepository;

import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class AgendamentoRepository implements IAgendamentoRepository {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirAgendamento(CadastrarAgendamentoDto dto){
        String sql = "INSERT INTO agendamento(cliente_id, data, horario, servico) VALUES(?,?,?,?)";
        Object[] parametros = new Object[4];
        parametros[0] = dto.getClienteId();
        parametros[1] = dto.getData();
        parametros[2] = dto.getHorario();
        parametros[3] = dto.getServico();
        jdbc.update(sql,parametros);
    }

    public List<Map<String,Object>> listarAgendamentos(){
        String sql = "SELECT a.id, a.data, a.horario, a.servico, c.nome FROM agendamento AS a LEFT JOIN cliente AS c ON c.id = a.cliente_id";
        return jdbc.queryForList(sql);
    }

    public List<Map<String,Object>> listarAgendamentoPorData(LocalDate data){
        String sql = "SELECT a.id, a.data, a.horario, a.servico, c.nome FROM agendamento AS a LEFT JOIN cliente AS c ON c.id = a.cliente_id where a.data = ?";
        return jdbc.queryForList(sql, data);
    }

    public Map<String,Object> listarAgendamentoPorId(int id){
        String sql = "SELECT a.id, a.data, a.horario, a.servico, c.nome FROM agendamento AS a LEFT JOIN cliente AS c ON c.id = a.cliente_id where a.id = ?";

        try {
            return jdbc.queryForMap(sql, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String,Object>> listarAgendamentosPorIdCliente(int id){
        String sql = "SELECT a.id, a.data, a.horario, a.servico, c.nome FROM agendamento AS a LEFT JOIN cliente AS c ON c.id = a.cliente_id where a.cliente_id = ?";

        try {
            return jdbc.queryForList(sql, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void atualizarAgendamento(int id, CadastrarAgendamentoDto novo){
        String sql = "UPDATE agendamento SET cliente_id = ?, data = ?, horario = ?, servico = ? where id = ?";
        Object[] parametros = new Object[5];
        parametros[0] = novo.getClienteId();
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

}
