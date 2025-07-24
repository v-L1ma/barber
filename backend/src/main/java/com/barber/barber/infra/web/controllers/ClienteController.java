package com.barber.barber.infra.web.controllers;

import com.barber.barber.application.services.TokenService.ITokenService;
import com.barber.barber.application.usecases.atualizarDadosCadastrais.IAtualizarDadosCadastraisUseCase;
import com.barber.barber.application.usecases.cadastrarCliente.ICadastrarClienteUseCase;
import com.barber.barber.application.usecases.deletarCliente.IDeletarClienteUseCase;
import com.barber.barber.application.usecases.loginCliente.ILoginClienteUseCase;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;
import com.barber.barber.infra.web.DTOs.LoginClienteDTO;
import com.barber.barber.infra.web.DTOs.LoginClienteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://time4barber.netlify.app")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ICadastrarClienteUseCase cadastrarClienteUseCase;
    private final ILoginClienteUseCase loginClienteUseCase;
    private final ITokenService tokenService;
    private final IAtualizarDadosCadastraisUseCase atualizarDadosCadastraisUseCase;
    private final IDeletarClienteUseCase deletarClienteUseCase;

    @Autowired
    public ClienteController(ICadastrarClienteUseCase cadastrarClienteUseCase, ITokenService tokenService, ILoginClienteUseCase loginClienteUseCase, IAtualizarDadosCadastraisUseCase atualizarDadosCadastraisUseCase, IDeletarClienteUseCase deletarClienteUseCase){
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.loginClienteUseCase = loginClienteUseCase;
        this.atualizarDadosCadastraisUseCase = atualizarDadosCadastraisUseCase;
        this.tokenService = tokenService;
        this.deletarClienteUseCase = deletarClienteUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginClienteResponseDTO> loginCliente(@RequestBody LoginClienteDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(loginClienteUseCase.executar(dto));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<CadastrarClienteResponseDto> cadastrarCliente(@RequestBody CadastrarClienteDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarClienteUseCase.executar(dto));
    }

    @PutMapping("/atualizar-dados/{id}")
    public ResponseEntity<CadastrarClienteResponseDto> atualizarDadosCliente(@PathVariable int id, @RequestBody CadastrarClienteDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(atualizarDadosCadastraisUseCase.executar(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CadastrarClienteResponseDto> deletarContaCliente(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(deletarClienteUseCase.executar(id));
    }

    @GetMapping("/validar-token")
    public ResponseEntity<Boolean> validarToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String subject = tokenService.validateToken(token);
        return ResponseEntity.ok(subject != null);
    }

}
