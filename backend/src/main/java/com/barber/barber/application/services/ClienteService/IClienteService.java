package com.barber.barber.application.services.ClienteService;

import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;

import java.time.LocalDate;
import java.util.List;

public interface IClienteService {

    void cadastrarCliente(CadastrarClienteDto dto);

    Cliente buscarClientePorEmail(String email);

    Cliente buscarClientePorId(int id);

    void atualizarDadosCliente(int id, Cliente novo);

    void deletarCliente(int id);

}
