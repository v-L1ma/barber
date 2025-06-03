package com.barber.barber.model.Agendamento;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Agendamento {

    private int id;
    private String cliente;
    private String data;
    private String horario;
    private String servico;

    public Agendamento(int id, String cliente, String data, String horario, String servico) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
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
        String data = (String) registro.get("data");
        String horario = (String) registro.get("horario");
        String servico = (String) registro.get("servico");

        return new Agendamento(id,cliente,data,horario,servico);
    }

    public static List<Agendamento> converterVarios(List<Map<String,Object>> registro){
        ArrayList<Agendamento> lista = new ArrayList<Agendamento>();
        for(Map<String,Object> reg : registro){
            lista.add(converter(reg));
        }
        return lista;
    }
}
