package com.barber.barber.controller;

import com.barber.barber.model.Agendamento.Agendamento;
import com.barber.barber.model.Agendamento.AgendamentoService;
import com.barber.barber.model.Agendamento.CadastrarAgendamentoDto;
import com.barber.barber.model.Agendamento.ListarAgendamentoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
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

    @GetMapping("/{data}")
    public ResponseEntity<ListarAgendamentoResponseDTO> listaPorData(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);
        var agendamentos = agendamentoService.listarAgendamentosPorData(data);

        if (agendamentos.isEmpty()){
            var response = new ListarAgendamentoResponseDTO("Esse cliente não possui agendamentos",agendamentos);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        var response = new ListarAgendamentoResponseDTO("Listagem dos agendamentos do dia " + data + "feita com sucesso!", agendamentos);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> criarAgendamento(@RequestBody CadastrarAgendamentoDto agendamentoDto){
        if (agendamentoDto.cliente() == null ||
                agendamentoDto.data() == null ||
                agendamentoDto.horario() == null||
                agendamentoDto.servico() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos os campos são obrigatórios.");
        }

        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();

        for (Agendamento item : agendamentos){
            if (item.getData().equals(agendamentoDto.data()) && item.getHorario().equals(agendamentoDto.horario())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um agendamento nessa data e nesse horário.");
        }
        }

        agendamentoService.inserirAgendamento(agendamentoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Agendamento feito com sucesso");
    }

    @PutMapping("/{id}")
    public void atualizarAgendamento(@PathVariable int id, @RequestBody Agendamento agendamento){

        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);

        agendamentoService.atualizarAgendamento(id, agendamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAgendamento(@PathVariable int id){

        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);
        agendamentoService.deletarAgendamento(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuario excluido com sucesso");
    }

}
