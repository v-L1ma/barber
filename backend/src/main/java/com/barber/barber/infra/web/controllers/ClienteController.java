package com.barber.barber.infra.web.controllers;

import com.barber.barber.application.usecases.cadastrarCliente.ICadastrarClienteUseCase;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ICadastrarClienteUseCase cadastrarClienteUseCase;

    @Autowired
    public ClienteController(ICadastrarClienteUseCase cadastrarClienteUseCase){
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
    }

    @PostMapping
    public ResponseEntity<CadastrarClienteResponseDto> cadastrarCliente(@RequestBody CadastrarClienteDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarClienteUseCase.executar(dto));
    }

}
