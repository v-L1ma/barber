package com.barber.barber.infra.web.controllers;

import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.domain.exceptions.NenhumAgendamentoEncontradoException;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;
import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.barber.barber.application.usecases.listarAgendamento.listarAgendamentoUseCase;
import com.barber.barber.application.usecases.criarAgendamento.criarAgendamentoUseCase;
import com.barber.barber.application.usecases.atualizarAgendamento.atualizarAgendamentoUseCase;
import com.barber.barber.application.usecases.listarAgendamentoPorData.listarAgendamentoPorDataUseCase;
import com.barber.barber.application.usecases.deletarAgendamento.deletarAgendamentoUseCase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebMvcTest(AgendamentoController.class)
class AgendamentoControllerTest {

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
    private criarAgendamentoUseCase criarAgendamentoUseCase;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Deve retornar uma resposta de sucesso e uma lista de agendamento")
    void listarAgendamentos() {
        Agendamento agendamento = new Agendamento(
                1,
                "Vinicius",
                LocalDate.now().plusDays(2),
                LocalTime.of(13,0), "Barba"
        );

        ListarAgendamentoResponseDTO response = new ListarAgendamentoResponseDTO("Listagem feita com sucesso!", List.of(agendamento));

        Mockito.when(listarAgendamentoUseCase.executar()).thenReturn(response);

        try {
            mockMvc.perform(get("/agendamento"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve retornar uma resposta de sucesso e uma lista de agendamento em uma data especifica")
    void listaPorData() {
        Agendamento agendamento = new Agendamento(
                1,
                "Vinicius",
                LocalDate.now().plusDays(2),
                LocalTime.of(13,0), "Barba"
        );

        LocalDate data = LocalDate.now().plusDays(2);

        ListarAgendamentoResponseDTO response = new ListarAgendamentoResponseDTO(
                "Listagem dos agendamentos do dia " + data + " feita com sucesso!",
                List.of(agendamento)
        );

        Mockito.when(listarAgendamentoPorDataUseCase.executar(data)).thenReturn(response);

        try {
            mockMvc.perform(get("/agendamento"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve retornar uma resposta de sucesso ao criar o agendamento")
    void criarAgendamento() {

        Agendamento agendamento = new Agendamento(
                1,
                "Vinicius",
                LocalDate.now().plusDays(2),
                LocalTime.of(13,0), "Barba"
        );

        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto(
                "Vinicius",
                LocalDate.now().plusDays(2),
                LocalTime.of(13,0),
                "Barba"
        );

        CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Agendamento feito com sucesso");

        Mockito.when(criarAgendamentoUseCase.executar(dto)).thenReturn(response);

        try {
            mockMvc.perform(post("/agendamento")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve retornar uma resposta de sucesso ao atualizar o agendamento")
    void atualizarAgendamento() {
        Agendamento agendamento = new Agendamento(
                1,
                "Vinicius",
                LocalDate.now().plusDays(2),
                LocalTime.of(13,0), "Barba"
        );

        int agendamentoId = 1;

        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto(
                "Vinicius",
                LocalDate.now().plusDays(2),
                LocalTime.of(13,0),
                "Barba"
        );

        CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Agendamento atualizado com sucesso.");

        Mockito.when(atualizarAgendamentoUseCase.executar(agendamentoId, agendamento)).thenReturn(response);

        try {
            mockMvc.perform(put("/agendamento/" + agendamentoId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(agendamento)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve retornar uma resposta de sucesso ao deletar o agendamento")
    void deletarAgendamento() {
        int agendamentoId = 1;

        CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Agendamento excluído com sucesso");

        Mockito.when(deletarAgendamentoUseCase.executar(agendamentoId)).thenReturn(response);

        try{

            mockMvc.perform(delete("/agendamento/"+agendamentoId))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}