package com.barber.barber.application.usecases.listarAgendamentosPorIdCliente;

import com.barber.barber.application.services.AgendamentoService.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.NenhumAgendamentoEncontradoException;
import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class listarAgendamentosPorIdClienteUseCase implements IListarAgendamentosPorIdClienteUseCase {

    private final IAgendamentoService agendamentoService;

    public listarAgendamentosPorIdClienteUseCase(IAgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    @Override
    public ListarAgendamentoResponseDTO executar(int id) {
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentosPorIdCliente(id);

        if (agendamentos.isEmpty()){
            throw new NenhumAgendamentoEncontradoException();
        }

        return new ListarAgendamentoResponseDTO("Listagem feita com sucesso!", agendamentos);
    }
}
