package com.barber.barber.controller;

import com.barber.barber.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.DTOs.CadastrarAgendamentoResponseDto;
import com.barber.barber.DTOs.ListarAgendamentoResponseDTO;
import com.barber.barber.interfaces.IAgendamentoService;
import com.barber.barber.model.Agendamento.Agendamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoControllerTest {

    @InjectMocks
    private AgendamentoController agendamentoController;

    @Mock
    private IAgendamentoService agendamentoService;

    @Test
    @DisplayName("Deve Retornar uma lista com os agendamentos")
    void ReturnaOklistarAgendamentos() {

        LocalDate localDate = LocalDate.parse("2025-02-02");
        LocalTime horario = LocalTime.parse("14:30");

        Agendamento agendamento = new Agendamento(1,"Vinicius", localDate, horario, "Barbar");
        List<Agendamento> agendamentos = List.of(agendamento);

        Mockito.when(agendamentoService.listarAgendamentos()).thenReturn(agendamentos);

        ResponseEntity<ListarAgendamentoResponseDTO> response = agendamentoController.listarAgendamentos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Listagem feita com sucesso!", response.getBody().message());
        assertEquals(1, response.getBody().agendamentos().size());
    }

    @Test
    @DisplayName("Deve Retornar Not Found se não houver agendamentos para listar")
    void RetornaNotFoundlistarAgendamentos() {

        Mockito.when(agendamentoService.listarAgendamentos()).thenReturn(Collections.emptyList());

        ResponseEntity<ListarAgendamentoResponseDTO> response = agendamentoController.listarAgendamentos();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Nenhum agendamento encontrado.", response.getBody().message());
        assertTrue(response.getBody().agendamentos().isEmpty());
    }

    @Test
    @DisplayName("Deve retornar uma lista de agendamentos em uma certa data")
    void RetornarOklistaPorData() {

        //arrange
        LocalDate localDate1 = LocalDate.parse("2025-02-02");
        LocalTime horario1 = LocalTime.parse("14:30");

        Agendamento agendamento1 = new Agendamento(1,"Vinicius", localDate1, horario1, "Barbar");

        LocalDate localDate2 = LocalDate.parse("2025-03-02");
        LocalTime horario2 = LocalTime.parse("14:30");

        Agendamento agendamento2 = new Agendamento(1,"Vinicius", localDate2, horario2, "Barbar");

        List<Agendamento> agendamentos = List.of(agendamento1);

        LocalDate localDate = LocalDate.parse("2025-02-02");
        Mockito.when(agendamentoService.listarAgendamentosPorData(localDate)).thenReturn(agendamentos);

        //act

        ResponseEntity<ListarAgendamentoResponseDTO> response = agendamentoController.listaPorData(localDate);

        //assert

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Listagem dos agendamentos do dia " + localDate + "feita com sucesso!", response.getBody().message());
        assertEquals(1, response.getBody().agendamentos().size());
    }

    @Test
    @DisplayName("Deve retornar um status de not found se não houver agendamentos")
    void RetornarNotFoundListarPorData(){

        LocalDate localDate = LocalDate.parse("2025-02-02");
        Mockito.when(agendamentoService.listarAgendamentosPorData(localDate)).thenReturn(Collections.emptyList());

        ResponseEntity<ListarAgendamentoResponseDTO> response = agendamentoController.listaPorData(localDate);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Nenhum agendamento encontrado.", response.getBody().message());
        assertTrue(response.getBody().agendamentos().isEmpty());
    }

    @Test
    @DisplayName("Deve criar um novo agendamento")
    void deveCriarAgendamento() {

        LocalDate data = LocalDate.parse("2025-03-02");
        LocalTime horario = LocalTime.parse("14:30");

        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto("Vinicius", data, horario, "Barba");

        ResponseEntity<CadastrarAgendamentoResponseDto> response = agendamentoController.criarAgendamento(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Agendamento feito com sucesso", response.getBody().message());
    }

    @Test
    @DisplayName("Já existe um agendamento nessa data e hora")
    void jaExisteCriarAgendamento() {

        LocalDate data = LocalDate.parse("2025-03-02");
        LocalTime horario = LocalTime.parse("14:30");

        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto("Vinicius", data, horario, "Barba");

        Agendamento existente = new Agendamento(1, "Outro Cliente", dto.data(), dto.horario(), "Barba");

        Mockito.when(agendamentoService.listarAgendamentos()).thenReturn(List.of(existente));

        ResponseEntity<CadastrarAgendamentoResponseDto> response = agendamentoController.criarAgendamento(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Já existe um agendamento nessa data e nesse horário.", response.getBody().message());
    }

    @Test
    @DisplayName("Deve retornar erro se algum campo estiver nulo")
    void criarAgendamentoComCamposNulos() {
        CadastrarAgendamentoDto dto = new CadastrarAgendamentoDto(null, null, null, null);

        ResponseEntity<CadastrarAgendamentoResponseDto> response = agendamentoController.criarAgendamento(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Todos os campos são obrigatórios.", response.getBody().message());
    }

    @Test
    void atualizarAgendamento() {
    }

    @Test
    void deletarAgendamento() {
    }
}