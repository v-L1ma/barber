package com.barber.barber.controller;

import com.barber.barber.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.DTOs.CadastrarAgendamentoResponseDto;
import com.barber.barber.DTOs.ListarAgendamentoResponseDTO;
import com.barber.barber.interfaces.IAgendamentoService;
import com.barber.barber.model.Agendamento.*;
import com.barber.barber.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "https://time4barber.netlify.app")
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final IAgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(IAgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public ResponseEntity<ListarAgendamentoResponseDTO> listarAgendamentos(){
        List<Agendamento>  agendamentos = agendamentoService.listarAgendamentos();

        if (agendamentos.isEmpty()){
            ListarAgendamentoResponseDTO response = new ListarAgendamentoResponseDTO("Nenhum agendamento encontrado.",agendamentos);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ListarAgendamentoResponseDTO response = new ListarAgendamentoResponseDTO("Listagem feita com sucesso!", agendamentos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{data}")
    public ResponseEntity<ListarAgendamentoResponseDTO> listaPorData(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        List<Agendamento>  agendamentos = agendamentoService.listarAgendamentosPorData(data);

        if (agendamentos.isEmpty()){
            ListarAgendamentoResponseDTO response = new ListarAgendamentoResponseDTO("Nenhum agendamento encontrado.",agendamentos);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ListarAgendamentoResponseDTO response = new ListarAgendamentoResponseDTO("Listagem dos agendamentos do dia " + data + "feita com sucesso!", agendamentos);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CadastrarAgendamentoResponseDto> criarAgendamento(@RequestBody CadastrarAgendamentoDto agendamentoDto){
        if (agendamentoDto.cliente() == null ||
                agendamentoDto.data() == null ||
                agendamentoDto.horario() == null||
                agendamentoDto.servico() == null){
            CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Todos os campos são obrigatórios.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();

        for (Agendamento item : agendamentos){
            if (item.getData().equals(agendamentoDto.data()) && item.getHorario().equals(agendamentoDto.horario())){

                CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Já existe um agendamento nessa data e nesse horário.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        }

        agendamentoService.inserirAgendamento(agendamentoDto);
        CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Agendamento feito com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadastrarAgendamentoResponseDto> atualizarAgendamento(@PathVariable int id, @RequestBody Agendamento agendamentoNovo){

        Agendamento agendamento = agendamentoService.listarAgendamentoPorId(id);

        if(agendamento == null){
            CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Nenhum agendamento encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        agendamentoService.atualizarAgendamento(id, agendamentoNovo);

        CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Agendamento atualizado com sucesso.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CadastrarAgendamentoResponseDto> deletarAgendamento(@PathVariable int id){

        Agendamento agendamento = agendamentoService.listarAgendamentoPorId(id);

        if(agendamento == null){
            CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Nenhum agendamento encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        agendamentoService.deletarAgendamento(id);

        CadastrarAgendamentoResponseDto response = new CadastrarAgendamentoResponseDto("Agendamento excluído com sucesso");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
