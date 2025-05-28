package com.barber.barber.controller;

import com.barber.barber.model.Agendamento.Agendamento;
import com.barber.barber.model.Agendamento.AgendamentoService;
import com.barber.barber.model.Agendamento.ListarAgendamentoResponseDTO;
import com.barber.barber.model.Cliente.ClienteService;
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
        var response = new ListarAgendamentoResponseDTO("Listagem feita com sucesso!", agendamentos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cliente}")
    public ResponseEntity<ListarAgendamentoResponseDTO> listaPorCliente(@PathVariable String cliente){
        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);
        var agendamentos = agendamentoService.listarAgendamentosPorCliente(cliente);
        var response = new ListarAgendamentoResponseDTO("Listagem do cliente " + cliente + "feita com sucesso!", agendamentos);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> criarAgendamento(@RequestBody Agendamento agendamento){
        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);
        agendamentoService.inserirAgendamento(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Agendamento feito com sucesso");
    }


}
