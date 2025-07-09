package com.barber.barber.domain.repositories.ClienteRepository;

import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;

import java.util.Map;

public interface IClienteRepository {
    void cadastrarCliente(CadastrarClienteDto dto);

    Map<String,Object> listarClientePorEmail(String email);

    void atualizarDadosCliente(int id, Cliente novo);

    void deletarCliente(int id);
}
