package com.barber.barber.application.usecases.deletarAgendamento;

import com.barber.barber.application.services.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.AgendamentoNaoEncontradoException;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class deletarAgendamentoUseCaseTest{

    @Mock
    private IAgendamentoService agendamentoService;

    @InjectMocks
    private deletarAgendamentoUseCase deletarAgendamentoUseCase;

    @Test
    @DisplayName("Deve lancar uma exception de agendamento não encontrado")
    void deveLancarExceptionAgendamentoNaoEncontrado() {
        int agendamentoId = 1;

        Mockito.when(agendamentoService.listarAgendamentoPorId(agendamentoId)).thenReturn(null);

        assertThrows(AgendamentoNaoEncontradoException.class, ()->{
            deletarAgendamentoUseCase.executar(agendamentoId);
        });
    }

    @Test
    @DisplayName("Deve retornar uma response dto de sucesso quando deletar o agendamento")
    void deveRetornarUmaResponseDeSucesso(){
        int agendamentoId = 1;

        Agendamento agendamento = new Agendamento(
                1,
                "Vinicius",
                LocalDate.now().plusDays(1),
                LocalTime.of(13, 0),
                "Barba"
        );

        Mockito.when(agendamentoService.listarAgendamentoPorId(agendamentoId)).thenReturn(agendamento);

        CadastrarAgendamentoResponseDto response = deletarAgendamentoUseCase.executar(agendamentoId);

        assertEquals("Agendamento excluído com sucesso", response.message());
    }
}