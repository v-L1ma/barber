package com.barber.barber.infra.web.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public record CadastrarAgendamentoDto(String cliente, LocalDate data, LocalTime horario, String servico) {
}
