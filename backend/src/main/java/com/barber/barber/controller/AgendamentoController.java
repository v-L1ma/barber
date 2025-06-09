package com.barber.barber.controller;

import com.barber.barber.model.Agendamento.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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

        var response = new ListarAgendamentoResponseDTO("Listagem dos agendamentos do dia " + data + "feita com sucesso!", agendamentos);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CadastrarAgendamentoResponseDto> criarAgendamento(@RequestBody CadastrarAgendamentoDto agendamentoDto){
        if (agendamentoDto.cliente() == null ||
                agendamentoDto.data() == null ||
                agendamentoDto.horario() == null||
                agendamentoDto.servico() == null){
            var response = new CadastrarAgendamentoResponseDto("Todos os campos são obrigatórios.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();

        for (Agendamento item : agendamentos){
            if (item.getData().equals(agendamentoDto.data()) && item.getHorario().equals(agendamentoDto.horario())){

                var response = new CadastrarAgendamentoResponseDto("Já existe um agendamento nessa data e nesse horário.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        }

        agendamentoService.inserirAgendamento(agendamentoDto);
        var response = new CadastrarAgendamentoResponseDto("Agendamento feito com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadastrarAgendamentoResponseDto> atualizarAgendamento(@PathVariable int id, @RequestBody Agendamento agendamento){

        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);

        agendamentoService.atualizarAgendamento(id, agendamento);

        var response = new CadastrarAgendamentoResponseDto("Agendamento atualizado com sucesso.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CadastrarAgendamentoResponseDto> deletarAgendamento(@PathVariable int id){

        AgendamentoService agendamentoService = ctx.getBean(AgendamentoService.class);
        agendamentoService.deletarAgendamento(id);

        var response = new CadastrarAgendamentoResponseDto("Usuário excluido com sucesso");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
