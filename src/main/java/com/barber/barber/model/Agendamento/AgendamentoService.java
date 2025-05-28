package com.barber.barber.model.Agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {
    @Autowired
    AgendamentoDAO agendamentoDAO;

    public void inserirAgendamento(Agendamento agendamento){
        agendamentoDAO.inserirAgendamento(agendamento);
    }

    public List<Agendamento> listarAgendamentos(){
        return agendamentoDAO.listarAgendamentos();
    }
}
