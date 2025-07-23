package com.barber.barber.domain.repositories.AgendamentoRepository;

import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.domain.entities.Agendamento.Agendamento;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAgendamentoRepository {

    void inserirAgendamento(CadastrarAgendamentoDto dto);

    List<Map<String,Object>> listarAgendamentos();

    List<Map<String,Object>> listarAgendamentoPorData(LocalDate data);

    Map<String,Object> listarAgendamentoPorId(int id);

    List<Map<String,Object>> listarAgendamentosPorIdCliente(int id);

    void atualizarAgendamento(int id, CadastrarAgendamentoDto novo);

    void deletarAgendamento(int id);

}
