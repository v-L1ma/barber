package com.barber.barber.application.usecases.criarAgendamento;

import com.barber.barber.application.services.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.AgendamentoJaExisteException;
import com.barber.barber.domain.exceptions.AgendamentoNaoPodeSerNoPassadoException;
import com.barber.barber.domain.exceptions.CamposObrigatoriosException;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class criarAgendamentoUseCase implements ICriarAgendamentoUseCase {

    private final IAgendamentoService agendamentoService;

    public criarAgendamentoUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
     public CadastrarAgendamentoResponseDto executar(CadastrarAgendamentoDto agendamentoDto){

        if (agendamentoDto.cliente() == null ||
                agendamentoDto.data() == null ||
                agendamentoDto.horario() == null||
                agendamentoDto.servico() == null){
            throw new CamposObrigatoriosException();
        }

        LocalDate today = LocalDate.now();
        if (agendamentoDto.data().isBefore(today)){
            throw new AgendamentoNaoPodeSerNoPassadoException();
        }

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();

        for (Agendamento item : agendamentos){
            if (item.getData().equals(agendamentoDto.data()) && item.getHorario().equals(agendamentoDto.horario())){
                throw new AgendamentoJaExisteException();
            }
        }

        agendamentoService.inserirAgendamento(agendamentoDto);
        
        return new CadastrarAgendamentoResponseDto("Agendamento feito com sucesso");
    
     }

}
