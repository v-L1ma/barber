package com.barber.barber.domain.repositories.ClienteRepository;

import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class ClienteRepository implements IClienteRepository{

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public void cadastrarCliente(CadastrarClienteDto dto) {
        String sql = "INSERT INTO cliente(nome, dataNascimento, email, celular, senha) VALUES(?, ?, ?, ?, ?)";
        Object[] parametros = new Object[5];

        parametros[0]= dto.getNome();
        parametros[1]=dto.getDataNascimento();
        parametros[2]=dto.getEmail();
        parametros[3]=dto.getCelular();
        parametros[4]=dto.getSenha();

        jdbc.update(sql,parametros);
    }

    @Override
    public Map<String, Object> listarClientePorEmail(String email) {
        String sql = "SELECT * FROM cliente where cliente.email = ?";
        List<Map<String, Object>> resultado = jdbc.queryForList(sql, email);

        return resultado.isEmpty() ? null : resultado.get(0);
    }

    @Override
    public Map<String, Object> listarClientePorId(int id) {
        String sql = "SELECT * FROM cliente where cliente.id = ?";
        Map<String, Object> resultado = jdbc.queryForMap(sql, id);

        return resultado.isEmpty() ? null : resultado;
    }

    @Override
    public void atualizarDadosCliente(int id, Cliente novo) {
        String sql = "UPDATE cliente SET nome = ?, dataNascimento = ?, email = ?, celular = ?, senha = ? WHERE id = ?";
        Object[] parametros = new Object[6];

        parametros[0] = novo.getNome();
        parametros[1] = novo.getDataNascimento();
        parametros[2] = novo.getEmail();
        parametros[3] = novo.getCelular();
        parametros[4] = novo.getSenha();
        parametros[5] = id;

        jdbc.update(sql, parametros);
    }

    @Override
    public void deletarCliente(int id) {
        String sql = "DELETE FROM agendamento WHERE cliente_id=?; DELETE FROM cliente WHERE id = ?";
        Object[] parametros = new Object[2];

        parametros[0]=id;
        parametros[1]=id;

        jdbc.update(sql,parametros);
    }
}
