package com.barber.barber.services;

import com.barber.barber.DAOs.AgendamentoDAO;
import com.barber.barber.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.model.Agendamento.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService {
    @Autowired
    AgendamentoDAO agendamentoDAO;

    public void inserirAgendamento(CadastrarAgendamentoDto dto){
        agendamentoDAO.inserirAgendamento(dto);
    }

    public List<Agendamento> listarAgendamentos(){
        return Agendamento.converterVarios(agendamentoDAO.listarAgendamentos());
    }

    public List<Agendamento> listarAgendamentosPorData(LocalDate data){
        return Agendamento.converterVarios(agendamentoDAO.listarAgendamentoPorData(data));
    }

    public void atualizarAgendamento(int id, Agendamento novo){
        agendamentoDAO.atualizarAgendamento(id, novo);
    }

    public Agendamento listarAgendamentoPorId(int id){
        return Agendamento.converter(agendamentoDAO.listarAgendamentoPorId(id));
    }

    public void deletarAgendamento(int id){
        agendamentoDAO.deletarAgendamento(id);
    }
}
