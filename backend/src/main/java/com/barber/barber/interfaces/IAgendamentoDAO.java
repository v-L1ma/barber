package com.barber.barber.interfaces;

import com.barber.barber.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.model.Agendamento.Agendamento;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAgendamentoDAO {

    void inserirAgendamento(CadastrarAgendamentoDto dto);

    List<Map<String,Object>> listarAgendamentos();

    List<Map<String,Object>> listarAgendamentoPorData(LocalDate data);

    Map<String,Object> listarAgendamentoPorId(int id);

    void atualizarAgendamento(int id, Agendamento novo);

    void deletarAgendamento(int id);

}
