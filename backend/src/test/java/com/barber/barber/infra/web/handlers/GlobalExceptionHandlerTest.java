package com.barber.barber.infra.web.handlers;

import com.barber.barber.application.services.AgendamentoService.IAgendamentoService;
import com.barber.barber.application.usecases.atualizarAgendamento.atualizarAgendamentoUseCase;
import com.barber.barber.application.usecases.criarAgendamento.criarAgendamentoUseCase;
import com.barber.barber.application.usecases.deletarAgendamento.deletarAgendamentoUseCase;
import com.barber.barber.application.usecases.listarAgendamento.listarAgendamentoUseCase;
import com.barber.barber.application.usecases.listarAgendamentoPorData.listarAgendamentoPorDataUseCase;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.*;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.infra.web.controllers.AgendamentoController;
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

@WebMvcTest(AgendamentoController.class)
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
    private IAgendamentoService agendamentoService;

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
    void handlerAgendamentoNaoEncontrado() throws Exception{

        Agendamento agendamentoNovo = new Agendamento(
                1,
                "dasdas",
                LocalDate.now(),
                LocalTime.of(13,0),
                "dasdasdsa");

        Mockito.when(atualizarAgendamentoUseCase.executar(Mockito.eq(999), Mockito.any()))
                .thenThrow(new AgendamentoNaoEncontradoException());

        try{
            mockMvc.perform(put("/agendamento/" + 999)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(agendamentoNovo)))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @DisplayName("Deve retornar 400 quando campos obrigatórios estiverem ausentes")
    void handlerCamposObrigatorios() {
        CadastrarAgendamentoDto dtoInvalido = new CadastrarAgendamentoDto();
        dtoInvalido.setClienteId("");
        dtoInvalido.setData(LocalDate.now());
        dtoInvalido.setHorario(LocalTime.of(13,0));
        dtoInvalido.setServico("");

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
        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto();
        dto.setClienteId("Vinicius");
        dto.setData(LocalDate.now().plusDays(1));
        dto.setHorario(LocalTime.of(10,0));
        dto.setServico("Corte");

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
        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto();

        dto.setClienteId("Vinicius");
        dto.setData(LocalDate.now().minusMonths(4).minusDays(2));
        dto.setHorario(LocalTime.of(10,0));
        dto.setServico("Corte");

        Mockito.when(criarAgendamentoUseCase.executar(Mockito.any()))
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