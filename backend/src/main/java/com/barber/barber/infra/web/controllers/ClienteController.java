package com.barber.barber.infra.web.controllers;

import com.barber.barber.application.services.TokenService.ITokenService;
import com.barber.barber.application.usecases.cadastrarCliente.ICadastrarClienteUseCase;
import com.barber.barber.application.usecases.loginCliente.ILoginClienteUseCase;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;
import com.barber.barber.infra.web.DTOs.LoginClienteDTO;
import com.barber.barber.infra.web.DTOs.LoginClienteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class ClienteController {

    private final ICadastrarClienteUseCase cadastrarClienteUseCase;
    private final ILoginClienteUseCase loginClienteUseCase;
    private final ITokenService tokenService;

    @Autowired
    public ClienteController(ICadastrarClienteUseCase cadastrarClienteUseCase, ITokenService tokenService, ILoginClienteUseCase loginClienteUseCase){
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.loginClienteUseCase = loginClienteUseCase;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginClienteResponseDTO> loginCliente(@RequestBody LoginClienteDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(loginClienteUseCase.executar(dto));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<CadastrarClienteResponseDto> cadastrarCliente(@RequestBody CadastrarClienteDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarClienteUseCase.executar(dto));
    }

}
