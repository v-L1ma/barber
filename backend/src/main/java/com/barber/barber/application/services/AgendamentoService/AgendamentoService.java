package com.barber.barber.application.services.AgendamentoService;

import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.domain.repositories.AgendamentoRepository.IAgendamentoRepository;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService implements IAgendamentoService {
    @Autowired
    IAgendamentoRepository agendamentoRepository;

    public void inserirAgendamento(CadastrarAgendamentoDto dto){
        agendamentoRepository.inserirAgendamento(dto);
    }

    public List<Agendamento> listarAgendamentos(){
        return Agendamento.converterVarios(agendamentoRepository.listarAgendamentos());
    }

    public List<Agendamento> listarAgendamentosPorData(LocalDate data){
        return Agendamento.converterVarios(agendamentoRepository.listarAgendamentoPorData(data));
    }

    public void atualizarAgendamento(int id, Agendamento novo){
        agendamentoRepository.atualizarAgendamento(id, novo);
    }

    public Agendamento listarAgendamentoPorId(int id){
        if(agendamentoRepository.listarAgendamentoPorId(id)==null){
            return null;
        }
        return Agendamento.converter(agendamentoRepository.listarAgendamentoPorId(id));
    }

    public void deletarAgendamento(int id){
        agendamentoRepository.deletarAgendamento(id);
    }
}
