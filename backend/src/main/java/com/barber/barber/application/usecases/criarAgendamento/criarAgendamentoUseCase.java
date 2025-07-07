package com.barber.barber.application.usecases.criarAgendamento;

import com.barber.barber.application.services.IAgendamentoService;
import com.barber.barber.application.services.rabbitMQService.RabbitmqService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.AgendamentoJaExisteException;
import com.barber.barber.domain.exceptions.AgendamentoNaoPodeSerNoPassadoException;
import com.barber.barber.domain.exceptions.CamposObrigatoriosException;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class criarAgendamentoUseCase implements ICriarAgendamentoUseCase {

    @Autowired
    private RabbitmqService rabbitmqService;

    private final IAgendamentoService agendamentoService;

    public criarAgendamentoUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
     public CadastrarAgendamentoResponseDto executar(CadastrarAgendamentoDto agendamentoDto){

        if (agendamentoDto.getCliente() == null ||
                agendamentoDto.getData() == null ||
                agendamentoDto.getHorario() == null||
                agendamentoDto.getServico() == null){
            throw new CamposObrigatoriosException();
        }

        LocalDate today = LocalDate.now();
        if (agendamentoDto.getData().isBefore(today)){
            throw new AgendamentoNaoPodeSerNoPassadoException();
        }

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();

        for (Agendamento item : agendamentos){
            if (item.getData().equals(agendamentoDto.getData()) && item.getHorario().equals(agendamentoDto.getHorario())){
                throw new AgendamentoJaExisteException();
            }
        }

        agendamentoService.inserirAgendamento(agendamentoDto);


        this.rabbitmqService.enviaMensagem("FILA_WHATSAPP", agendamentoDto);
        return new CadastrarAgendamentoResponseDto("Agendamento feito com sucesso");
     }

}
