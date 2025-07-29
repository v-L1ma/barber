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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://time4barber.netlify.app", allowCredentials = "true")
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
    public ResponseEntity<LoginClienteResponseDTO> loginCliente(@RequestBody LoginClienteDTO dto, HttpServletResponse response){
        LoginClienteResponseDTO result = loginClienteUseCase.executar(dto);

        Cookie cookie = new Cookie("token", result.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setAttribute("SameSite", "None");

        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK).body(result);
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
    public ResponseEntity<Boolean> validarToken(HttpServletRequest request) {
        String token = null;

        if(request.getCookies() !=null){
            for (Cookie cookie : request.getCookies()){
                if ("token".equals(cookie.getName())){
                    token= cookie.getValue();
                    break;
                }
            }
        }

        String subject = token !=null ? tokenService.validateToken(token) : null;

        return ResponseEntity.ok(subject != null);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.noContent().build();
    }

}
