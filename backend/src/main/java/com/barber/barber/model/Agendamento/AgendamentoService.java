package com.barber.barber.model.Agendamento;

import com.barber.barber.model.Cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public List<Agendamento> listarAgendamentosPorCliente(String data){
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
