package com.barber.barber.model.Agendamento;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Agendamento {

    private int id;
    private String cliente;
    private LocalDate data;
    private LocalTime horario;
    private String servico;

    public Agendamento(int id, String cliente, LocalDate data, LocalTime horario, String servico) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.horario = horario;
        this.servico = servico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    public static Agendamento converter(Map<String,Object> registro){
        int id = (Integer) registro.get("id");

        String cliente = (String) registro.get("cliente");

        Date dataSql = (Date) registro.get("data");
        LocalDate data = dataSql.toLocalDate();

        Time horarioSql = (Time) registro.get("horario");
        LocalTime horario = horarioSql.toLocalTime();

        String servico = (String) registro.get("servico");

        return new Agendamento(id,cliente,data,horario,servico);
    }

    public static List<Agendamento> converterVarios(List<Map<String,Object>> registro){
        ArrayList<Agendamento> lista = new ArrayList<>();
        for(Map<String,Object> reg : registro){
            lista.add(converter(reg));
        }
        return lista;
    }
}
