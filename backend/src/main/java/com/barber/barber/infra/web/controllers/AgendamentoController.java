package com.barber.barber.infra.web.controllers;

import com.barber.barber.application.usecases.atualizarAgendamento.IAtualizarAgendamentoUseCase;
import com.barber.barber.application.usecases.criarAgendamento.ICriarAgendamentoUseCase;
import com.barber.barber.application.usecases.deletarAgendamento.IDeletarAgendamentoUseCase;
import com.barber.barber.application.usecases.listarAgendamento.IListarAgendamentoUseCase;
import com.barber.barber.application.usecases.listarAgendamentoPorData.IListarAgendamentoPorDataUseCase;
import com.barber.barber.application.usecases.listarAgendamentosPorIdCliente.IListarAgendamentosPorIdClienteUseCase;
import com.barber.barber.domain.entities.Agendamento.Agendamento;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoDto;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;
import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final IListarAgendamentoUseCase listarAgendamentoUseCase;
    private final IAtualizarAgendamentoUseCase atualizarAgendamentoUseCase;
    private final IDeletarAgendamentoUseCase deletarAgendamentoUseCase;
    private final IListarAgendamentoPorDataUseCase listarAgendamentoPorDataUseCase;
    private final ICriarAgendamentoUseCase criarAgendamentoUseCase;
    private final IListarAgendamentosPorIdClienteUseCase listarAgendamentosPorIdClienteUseCase;

    @Autowired
    public AgendamentoController(
            IListarAgendamentoUseCase listarAgendamentoUseCase,
            IAtualizarAgendamentoUseCase atualizarAgendamentoUseCase,
            IDeletarAgendamentoUseCase deletarAgendamentoUseCase,
            IListarAgendamentoPorDataUseCase listarAgendamentoPorDataUseCase,
            ICriarAgendamentoUseCase criarAgendamentoUseCase,
            IListarAgendamentosPorIdClienteUseCase listarAgendamentosPorIdClienteUseCase
    ) {
        this.listarAgendamentoUseCase = listarAgendamentoUseCase;
        this.atualizarAgendamentoUseCase = atualizarAgendamentoUseCase;
        this.deletarAgendamentoUseCase = deletarAgendamentoUseCase;
        this.listarAgendamentoPorDataUseCase = listarAgendamentoPorDataUseCase;
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.listarAgendamentosPorIdClienteUseCase = listarAgendamentosPorIdClienteUseCase;
    }

    @GetMapping
    public ResponseEntity<ListarAgendamentoResponseDTO> listarAgendamentos(){
        return ResponseEntity.ok(listarAgendamentoUseCase.executar());
    }

    @GetMapping("/{data}")
    public ResponseEntity<ListarAgendamentoResponseDTO> listaPorData(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        return ResponseEntity.ok(listarAgendamentoPorDataUseCase.executar(data));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ListarAgendamentoResponseDTO> listaPorData(@PathVariable int id){
        return ResponseEntity.ok(listarAgendamentosPorIdClienteUseCase.executar(id));
    }

    @PostMapping
    public ResponseEntity<CadastrarAgendamentoResponseDto> criarAgendamento(@RequestBody CadastrarAgendamentoDto agendamentoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(criarAgendamentoUseCase.executar(agendamentoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadastrarAgendamentoResponseDto> atualizarAgendamento(@PathVariable int id, @RequestBody CadastrarAgendamentoDto agendamentoNovo){
        return ResponseEntity.status(HttpStatus.OK).body(atualizarAgendamentoUseCase.executar(id, agendamentoNovo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CadastrarAgendamentoResponseDto> deletarAgendamento(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(deletarAgendamentoUseCase.executar(id));
    }

}
