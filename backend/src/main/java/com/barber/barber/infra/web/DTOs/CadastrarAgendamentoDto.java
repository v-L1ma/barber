package com.barber.barber.infra.web.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public class CadastrarAgendamentoDto {
    String clienteId;
    LocalDate data;
    LocalTime horario;
    String servico;

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String cliente) {
        this.clienteId = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }
}
