package com.barber.barber.application.usecases.listarAgendamentoPorData;

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
class listarAgendamentoPorDataUseCaseTest {

    @Mock
    private IAgendamentoService agendamentoService;

    @InjectMocks
    private listarAgendamentoPorDataUseCase listarAgendamentoPorDataUseCase;

    @Test
    @DisplayName("Deve retornar uma exception de nenhum agendamento encontrado")
    void deveLancarExceptionNenhumAgendamentoEncontrado() {
        LocalDate data = LocalDate.now().plusDays(2);

        Mockito.when(agendamentoService.listarAgendamentosPorData(data)).thenReturn(Collections.emptyList());

        assertThrows(NenhumAgendamentoEncontradoException.class, ()->{
            listarAgendamentoPorDataUseCase.executar(data);
        });
    }

    @Test
    @DisplayName("Deve retornar uma response de sucesso com uma mensagem e uma lista de agendamentos")
    void deveRetornarUmaResponseDeSucesso(){
        LocalDate data = LocalDate.now().plusDays(2);

        Agendamento agendamento = new Agendamento(
                1,
                "Vinicius",
                LocalDate.now().plusDays(2),
                LocalTime.of(13, 0),
                "Barba"
        );

        Mockito.when(agendamentoService.listarAgendamentosPorData(data)).thenReturn(List.of(agendamento));

        ListarAgendamentoResponseDTO reponse = listarAgendamentoPorDataUseCase.executar(data);

        assertEquals(List.of(agendamento), reponse.agendamentos());
        assertEquals("Listagem dos agendamentos do dia " + data + " feita com sucesso!", reponse.message());
    }
}