package com.barber.barber.application.usecases.listarAgendamento;

import com.barber.barber.application.services.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.NenhumAgendamentoEncontradoException;
import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class listarAgendamentoUseCase implements IListarAgendamentoUseCase {

    private final IAgendamentoService agendamentoService;

    public listarAgendamentoUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
    public ListarAgendamentoResponseDTO executar() {
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();

        if (agendamentos.isEmpty()){
            throw new NenhumAgendamentoEncontradoException();
        }

        return new ListarAgendamentoResponseDTO("Listagem feita com sucesso!", agendamentos);
    }
}
