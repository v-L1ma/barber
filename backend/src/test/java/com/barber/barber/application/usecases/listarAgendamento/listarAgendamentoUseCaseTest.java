package com.barber.barber.application.usecases.listarAgendamento;

import com.barber.barber.application.services.AgendamentoService.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.NenhumAgendamentoEncontradoException;
import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class listarAgendamentoUseCaseTest {

    @Mock
    private IAgendamentoService agendamentoService;

    @InjectMocks
    private listarAgendamentoUseCase listarAgendamentoUseCase;

    @Test
    @DisplayName("Deve retornar uma exception de nenhum agendamento encontrado")
    void deveLancarExceptionNenhumAgendamentoEncontrado() {

        Mockito.when(agendamentoService.listarAgendamentos()).thenReturn(Collections.emptyList());

        assertThrows(NenhumAgendamentoEncontradoException.class, ()->{
            listarAgendamentoUseCase.executar();
        });
    }

    @Test
    @DisplayName("Deve retornar uma response de sucesso com uma mensagem e uma lista de agendamentos")
    void deveRetornarUmaResponseDeSucesso(){

        Agendamento agendamento = new Agendamento(
                1,
                "Vinicius",
                LocalDate.now().plusDays(1),
                LocalTime.of(13, 0),
                "Barba"
        );

        Mockito.when(agendamentoService.listarAgendamentos()).thenReturn(List.of(agendamento));

        ListarAgendamentoResponseDTO reponse = listarAgendamentoUseCase.executar();

        assertEquals(List.of(agendamento), reponse.agendamentos());
        assertEquals("Listagem feita com sucesso!", reponse.message());
    }
}