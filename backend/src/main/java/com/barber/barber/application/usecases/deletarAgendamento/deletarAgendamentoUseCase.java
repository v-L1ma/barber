package com.barber.barber.application.usecases.deletarAgendamento;

import com.barber.barber.application.services.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.AgendamentoNaoEncontradoException;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;
import org.springframework.stereotype.Service;

@Service
public class deletarAgendamentoUseCase implements IDeletarAgendamentoUseCase {

    private final IAgendamentoService agendamentoService;

    public deletarAgendamentoUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
    public CadastrarAgendamentoResponseDto executar(int id){
        
         Agendamento agendamento = agendamentoService.listarAgendamentoPorId(id);

        if(agendamento == null){
            throw new AgendamentoNaoEncontradoException();
        }

        agendamentoService.deletarAgendamento(id);

        return new CadastrarAgendamentoResponseDto("Agendamento excluído com sucesso");
    }

}
