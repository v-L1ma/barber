package com.barber.barber.application.services.AgendamentoService;

import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.domain.entities.Agendamento.Agendamento;

import java.time.LocalDate;
import java.util.List;

public interface IAgendamentoService {

    void inserirAgendamento(CadastrarAgendamentoDto dto);

    List<Agendamento> listarAgendamentos();

    List<Agendamento> listarAgendamentosPorData(LocalDate data);

    List<Agendamento> listarAgendamentosPorIdCliente(int id);

    void atualizarAgendamento(int id, CadastrarAgendamentoDto novo);

    Agendamento listarAgendamentoPorId(int id);

    void deletarAgendamento(int id);

}
