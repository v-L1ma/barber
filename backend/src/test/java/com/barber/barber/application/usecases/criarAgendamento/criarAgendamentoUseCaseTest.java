package com.barber.barber.application.usecases.criarAgendamento;

import com.barber.barber.application.services.AgendamentoService.IAgendamentoService;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.AgendamentoJaExisteException;
import com.barber.barber.domain.exceptions.AgendamentoNaoPodeSerNoPassadoException;
import com.barber.barber.domain.exceptions.CamposObrigatoriosException;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class criarAgendamentoUseCaseTest {

    @Mock
    private IAgendamentoService agendamentoService;

    @InjectMocks
    private criarAgendamentoUseCase criarAgendamentoUseCase;

    @Test
    @DisplayName("Deve lançar uma exception de Campos obrigatórios")
    void deveLancarExceptionCamposObrigatorios() {
        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto();
        dto.setClienteId("Vinicius");
        dto.setData(null);
        dto.setHorario(null);
        dto.setServico("Corte");

        assertThrows(CamposObrigatoriosException.class, ()->{
            criarAgendamentoUseCase.executar(dto);
        });

    }

    @Test
    @DisplayName("Deve lançar uma exception se a data do agendamento for no passado")
    void deveLancarExceptionAgendamentoNaoPodeSerNoPassado(){
        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto();
        dto.setClienteId("Vinicius");
        dto.setData(LocalDate.now().minusMonths(2));
        dto.setHorario(LocalTime.of(10,0));
        dto.setServico("Corte");

        assertThrows(AgendamentoNaoPodeSerNoPassadoException.class, ()->{
            criarAgendamentoUseCase.executar(dto);
        });
    }

    @Test
    @DisplayName("Deve lançar uma exception se já existir um agendamento na mesma data e hora")
    void deveLancarExceptionAgendamentoJaExiste(){
        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto();
        dto.setClienteId("Vinicius");
        dto.setData(LocalDate.now().plusDays(2));
        dto.setHorario(LocalTime.of(13,0));
        dto.setServico("Corte");

        Agendamento agendamento = new Agendamento(
                1,
                "Vinicius",
                LocalDate.now().plusDays(2),
                LocalTime.of(13,0), "Barba"
        );

        List<Agendamento> agendamentos = List.of(agendamento);

        Mockito.when(agendamentoService.listarAgendamentos()).thenReturn(agendamentos);

        assertThrows(AgendamentoJaExisteException.class,()->{
            criarAgendamentoUseCase.executar(dto);
        });
    }

//    @Test
//    @DisplayName("Deve retornar uma response de sucesso")
//    void deveRetornarUmaResponseDeSucesso(){
//
//        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto(
//                "Vinicius",
//                LocalDate.now().plusDays(2),
//                LocalTime.of(13,0), "Barba"
//        );
//
//        Mockito.when(agendamentoService.listarAgendamentos()).thenReturn(Collections.emptyList());
//
//        CadastrarAgendamentoResponseDto response = criarAgendamentoUseCase.executar(dto);
//
//        assertEquals("Agendamento feito com sucesso", response.message());
//    }
}