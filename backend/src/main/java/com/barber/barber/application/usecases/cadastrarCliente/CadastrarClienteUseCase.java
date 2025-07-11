package com.barber.barber.application.usecases.cadastrarCliente;

import com.barber.barber.application.services.ClienteService.IClienteService;
import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.domain.exceptions.AgendamentoJaExisteException;
import com.barber.barber.domain.exceptions.AgendamentoNaoPodeSerNoPassadoException;
import com.barber.barber.domain.exceptions.CamposObrigatoriosException;
import com.barber.barber.domain.exceptions.ContaJaCadastradaException;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CadastrarClienteUseCase implements ICadastrarClienteUseCase{

    private final IClienteService clienteService;
    private final PasswordEncoder passwordEncoder;

    public CadastrarClienteUseCase(IClienteService clienteService, PasswordEncoder passwordEncoder){

        this.clienteService = clienteService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CadastrarClienteResponseDto executar(CadastrarClienteDto dto) {
        if (dto.getNome() ==null ||
            dto.getDataNascimento() == null ||
            dto.getEmail() == null ||
            dto.getCelular() == null||
            dto.getSenha() == null){
            throw new CamposObrigatoriosException();
        }

        LocalDate yesterday = LocalDate.now().minusDays(1);
        if (dto.getDataNascimento().isAfter(yesterday)){
            throw new AgendamentoNaoPodeSerNoPassadoException("A data de nascimento não pode ser no futuro");
        }

        Cliente isClienteJaCadastrado = clienteService.buscarClientePorEmail(dto.getEmail());

        if (isClienteJaCadastrado!=null){
            throw new ContaJaCadastradaException();
        }

        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        clienteService.cadastrarCliente(dto);
        return new CadastrarClienteResponseDto("Cliente cadastrado com sucesso");
    }

}
