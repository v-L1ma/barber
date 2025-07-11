package com.barber.barber.application.usecases.loginCliente;

import com.barber.barber.application.services.ClienteService.IClienteService;
import com.barber.barber.application.services.TokenService.ITokenService;
import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.domain.exceptions.AgendamentoNaoEncontradoException;
import com.barber.barber.domain.exceptions.SenhaInvalidaException;
import com.barber.barber.domain.exceptions.UsuarioNaoCadastradoException;
import com.barber.barber.infra.web.DTOs.LoginClienteDTO;
import com.barber.barber.infra.web.DTOs.LoginClienteResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginClienteUseCase implements ILoginClienteUseCase{

    private final IClienteService clienteService;
    private final PasswordEncoder passwordEncoder;
    private final ITokenService tokenService;

    public LoginClienteUseCase(IClienteService clienteService, PasswordEncoder passwordEncoder, ITokenService tokenService){
        this.clienteService = clienteService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public LoginClienteResponseDTO executar(LoginClienteDTO dto) {
        Cliente cliente = this.clienteService.buscarClientePorEmail(dto.getEmail());

        if (cliente==null){
            throw new UsuarioNaoCadastradoException("Usuario Não Cadastrado");
        }

        boolean isSenhaValida = passwordEncoder.matches(dto.getSenha(), cliente.getSenha());

        if (!isSenhaValida){
           throw new SenhaInvalidaException("Senha inválida");
        }

        String token = this.tokenService.generateToken(cliente);
        return new LoginClienteResponseDTO("Login feito com sucesso!", token);
    }
}
