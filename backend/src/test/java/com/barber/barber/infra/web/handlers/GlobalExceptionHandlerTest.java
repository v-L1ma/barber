package com.barber.barber.infra.web.handlers;

import com.barber.barber.application.usecases.atualizarAgendamento.atualizarAgendamentoUseCase;
import com.barber.barber.application.usecases.criarAgendamento.criarAgendamentoUseCase;
import com.barber.barber.application.usecases.deletarAgendamento.deletarAgendamentoUseCase;
import com.barber.barber.application.usecases.listarAgendamento.listarAgendamentoUseCase;
import com.barber.barber.application.usecases.listarAgendamentoPorData.listarAgendamentoPorDataUseCase;
import com.barber.barber.application.services.AgendamentoService;
import com.barber.barber.domain.exceptions.*;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private listarAgendamentoUseCase listarAgendamentoUseCase;

    @MockitoBean
    private atualizarAgendamentoUseCase atualizarAgendamentoUseCase;

    @MockitoBean
    private deletarAgendamentoUseCase deletarAgendamentoUseCase;

    @MockitoBean
    private listarAgendamentoPorDataUseCase listarAgendamentoPorDataUseCase;

    @MockitoBean
    private AgendamentoService agendamentoService;

    @MockitoBean
    private criarAgendamentoUseCase criarAgendamentoUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar 404 quando nenhum agendamento for encontrado")
    void handlerNenhumAgendamentoEncontrado() {
        Mockito.when(listarAgendamentoUseCase.executar())
                .thenThrow(new NenhumAgendamentoEncontradoException("Nenhum agendamento encontrado"));
        try{
        mockMvc.perform(get("/agendamento"))
                .andExpect(status().isNotFound());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve retornar 404 quando agendamento não for encontrado")
    void handlerAgendamentoNaoEncontrado() {

        int agendamentoId = 999;

        Mockito.when(agendamentoService.listarAgendamentoPorId(agendamentoId))
                .thenThrow(new AgendamentoNaoEncontradoException("Agendamento não encontrado"));

        try{
            mockMvc.perform(get("/agendamento/" + agendamentoId))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @DisplayName("Deve retornar 400 quando campos obrigatórios estiverem ausentes")
    void handlerCamposObrigatorios() {
        CadastrarAgendamentoDto dtoInvalido = new CadastrarAgendamentoDto(
                "",
                null,
                null,
                "");

        Mockito.when(criarAgendamentoUseCase.executar(Mockito.any()))
                .thenThrow(new CamposObrigatoriosException("Campos obrigatórios não preenchidos"));

        try {
            mockMvc.perform(post("/agendamento")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dtoInvalido)))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve retornar 400 quando o agendamento já existir")
    void handlerAgendamentoJaExiste() {
        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto(
                "Vinicius",
                LocalDate.now().plusDays(1),
                LocalTime.of(10, 0),
                "Corte");

        Mockito.when(criarAgendamentoUseCase.executar(Mockito.any()))
                .thenThrow(new AgendamentoJaExisteException("Agendamento já existe para esse horário"));

        try{
        mockMvc.perform(post("/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve retornar 400 quando agendamento for no passado")
    void handlerAgendamentoNaoPodeSerNoPassado() {
        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto(
                "Vinicius",
                LocalDate.now().minusMonths(2),
                LocalTime.of(10, 0),
                "Barba");

        Mockito.when(criarAgendamentoUseCase.executar(dto))
                .thenThrow(new AgendamentoNaoPodeSerNoPassadoException("Não é possível agendar datas no passado"));

        try {
            mockMvc.perform(post("/agendamento")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}