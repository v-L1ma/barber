package com.barber.barber.controller;

import com.barber.barber.model.Agendamento.Agendamento;
import com.barber.barber.model.Agendamento.AgendamentoService;
import com.barber.barber.model.Agendamento.ListarAgendamentoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    ApplicationContext ctx;

    @GetMapping
    public ResponseEntity<ListarAgendamentoResponseDTO> listarAgendamentos(){
        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);
        var agendamentos = agendamentoService.listarAgendamentos();

        if (agendamentos.isEmpty()){
            var response = new ListarAgendamentoResponseDTO("Nenhum agendamento encontrado.",agendamentos);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        var response = new ListarAgendamentoResponseDTO("Listagem feita com sucesso!", agendamentos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cliente}")
    public ResponseEntity<ListarAgendamentoResponseDTO> listaPorCliente(@PathVariable String cliente){
        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);
        var agendamentos = agendamentoService.listarAgendamentosPorCliente(cliente);

        if (agendamentos.isEmpty()){
            var response = new ListarAgendamentoResponseDTO("Esse cliente não possui agendamentos",agendamentos);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        var response = new ListarAgendamentoResponseDTO("Listagem do cliente " + cliente + "feita com sucesso!", agendamentos);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> criarAgendamento(@RequestBody Agendamento agendamento){
        if (agendamento.getCliente() == null ||
                agendamento.getData() == null ||
                agendamento.getHorario() == null||
                agendamento.getServico() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos os campos são obrigatórios.");
        }

        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();

        for (Agendamento item : agendamentos){
            if (item.getData().equals(agendamento.getData()) && item.getHorario().equals(agendamento.getHorario())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um agendamento nessa data e nesse horário.");
        }
        }

        agendamentoService.inserirAgendamento(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Agendamento feito com sucesso");
    }


}
