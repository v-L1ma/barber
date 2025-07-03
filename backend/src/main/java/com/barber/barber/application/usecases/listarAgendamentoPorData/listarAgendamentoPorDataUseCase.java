package com.barber.barber.application.usecases.listarAgendamentoPorData;

import com.barber.barber.application.services.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.NenhumAgendamentoEncontradoException;
import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class listarAgendamentoPorDataUseCase implements IListarAgendamentoPorDataUseCase {

    private final IAgendamentoService agendamentoService;

    public listarAgendamentoPorDataUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
    public ListarAgendamentoResponseDTO executar(LocalDate data){

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentosPorData(data);

        if (agendamentos.isEmpty()){
            throw new NenhumAgendamentoEncontradoException();
        }

        return new ListarAgendamentoResponseDTO("Listagem dos agendamentos do dia " + data + " feita com sucesso!", agendamentos);
    }

}
