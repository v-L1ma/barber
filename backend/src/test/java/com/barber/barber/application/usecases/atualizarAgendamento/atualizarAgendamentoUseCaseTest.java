package com.barber.barber.application.usecases.atualizarAgendamento;

import com.barber.barber.application.services.AgendamentoService.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.AgendamentoJaExisteException;
import com.barber.barber.domain.exceptions.AgendamentoNaoEncontradoException;
import com.barber.barber.domain.exceptions.AgendamentoNaoPodeSerNoPassadoException;
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
class atualizarAgendamentoUseCaseTest {

    @Mock
    private IAgendamentoService agendamentoService;

    @InjectMocks
    private atualizarAgendamentoUseCase atualizarAgendamentoUseCase;

    @Test
    @DisplayName("Deve Retornar uma exception de nenhum agendamento encontrado")
    void deveLancarExcecaoNenhumAgendamentoEncontrado() {
        int agendamentoId = 1;

        Mockito.when(agendamentoService.listarAgendamentoPorId(agendamentoId)).thenReturn(null);

        Agendamento agendamentoNovo = new Agendamento(1,"vinicius",  LocalDate.now().plusDays(2),
                LocalTime.of(10, 0), "Barba");

        assertThrows(AgendamentoNaoEncontradoException.class, ()->{
            atualizarAgendamentoUseCase.executar(agendamentoId,agendamentoNovo);
        });
    }

    @Test
    @DisplayName("Deve retornar uma exception de que o agendamento não pode ser no passado")
    void deveLancarExcecaoAgendamentoNaoPodeSerNoPassado(){
        int agendamentoId = 1;

        Agendamento agendamentoNovo = new Agendamento(1,"vinicius",  LocalDate.now().minusMonths(4),
                LocalTime.of(10, 0), "Barba");

        Agendamento agendamento = new Agendamento(1,"vinicius",  LocalDate.now().plusDays(2),
                LocalTime.of(10, 0), "Barba");

        Mockito.when(agendamentoService.listarAgendamentoPorId(agendamentoId)).thenReturn(agendamento);

        assertThrows(AgendamentoNaoPodeSerNoPassadoException.class, ()->{
            atualizarAgendamentoUseCase.executar(agendamentoId,agendamentoNovo);
        });
    }

    @Test
    @DisplayName("Deve retornar uma exception de agendamento já existe")
    void deveLancarUmaExceptionAgendamentoJaExiste(){

        int agendamentoId = 1;

        Agendamento agendamentoNovo = new Agendamento(1,"vinicius",  LocalDate.now().plusDays(2),
                LocalTime.of(10, 0), "Barba");

        Agendamento agendamento = new Agendamento(1,"vinicius",  LocalDate.now().plusDays(2),
                LocalTime.of(10, 0), "Barba");

        Mockito.when(agendamentoService.listarAgendamentoPorId(agendamentoId)).thenReturn(agendamento);

        assertThrows(AgendamentoJaExisteException.class, ()->{
            atualizarAgendamentoUseCase.executar(agendamentoId,agendamentoNovo);
        });

    }

    @Test
    @DisplayName("Deve retornar uma response de agendamento atualizado com sucesso")
    void deveRetornarUmCadastrarAgendamentoResponseDto(){

        int agendamentoId = 1;

        Agendamento agendamentoNovo = new Agendamento(1,"vinicius",  LocalDate.now().plusDays(2),
                LocalTime.of(10, 0), "Barba");

        Agendamento agendamento = new Agendamento(1,"vinicius",  LocalDate.now().plusDays(6),
                LocalTime.of(10, 0), "Barba");

        Mockito.when(agendamentoService.listarAgendamentoPorId(agendamentoId)).thenReturn(agendamento);

        CadastrarAgendamentoResponseDto response = atualizarAgendamentoUseCase.executar(agendamentoId, agendamentoNovo);

        assertEquals("Agendamento atualizado com sucesso.", response.message());
    }

}