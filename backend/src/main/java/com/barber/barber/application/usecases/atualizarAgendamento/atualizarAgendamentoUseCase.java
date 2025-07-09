package com.barber.barber.application.usecases.atualizarAgendamento;

import com.barber.barber.application.services.AgendamentoService.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.AgendamentoJaExisteException;
import com.barber.barber.domain.exceptions.AgendamentoNaoEncontradoException;
import com.barber.barber.domain.exceptions.AgendamentoNaoPodeSerNoPassadoException;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class atualizarAgendamentoUseCase implements IAtualizarAgendamentoUseCase{

    private final IAgendamentoService agendamentoService;

    public atualizarAgendamentoUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
    public CadastrarAgendamentoResponseDto executar(int id, Agendamento agendamentoNovo){
        Agendamento agendamento = agendamentoService.listarAgendamentoPorId(id);

        if(agendamento == null){
            throw new AgendamentoNaoEncontradoException();
        }

        LocalDate today = LocalDate.now();
        if (agendamentoNovo.getData().isBefore(today)){
            throw new AgendamentoNaoPodeSerNoPassadoException();
        }

        if (agendamento.getData().equals(agendamentoNovo.getData()) && agendamento.getHorario().equals(agendamentoNovo.getHorario())){
            throw new AgendamentoJaExisteException();
        }

        agendamentoService.atualizarAgendamento(id, agendamentoNovo);

        return new CadastrarAgendamentoResponseDto("Agendamento atualizado com sucesso.");
    }

}
