package com.barber.barber.application.usecases.cadastrarCliente;

import com.barber.barber.application.services.ClienteService.IClienteService;
import com.barber.barber.application.services.rabbitMQService.RabbitmqService;
import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.domain.exceptions.*;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;
import com.barber.barber.infra.web.DTOs.EmailDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CadastrarClienteUseCase implements ICadastrarClienteUseCase{

    private final IClienteService clienteService;
    private final PasswordEncoder passwordEncoder;
    private final RabbitmqService rabbitmqService;

    public CadastrarClienteUseCase(IClienteService clienteService, PasswordEncoder passwordEncoder, RabbitmqService rabbitmqService){

        this.clienteService = clienteService;
        this.passwordEncoder = passwordEncoder;
        this.rabbitmqService = rabbitmqService;
    }

    @Override
    public CadastrarClienteResponseDto executar(CadastrarClienteDto dto) {
        if (dto.getNome() ==null ||
            dto.getDataNascimento() == null ||
            dto.getEmail() == null ||
            dto.getCelular() == null||
            dto.getSenha() == null ||
            dto.getConfirmarSenha() == null){
            throw new CamposObrigatoriosException();
        }

        if (!dto.getConfirmarSenha().equals(dto.getSenha())){
            throw new SenhaInvalidaException("As senhas não conferem.");
        }

        if (dto.getSenha().length()<5){
            throw new CamposObrigatoriosException("A senha deve ter no minimo 5 caracteres");
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

        rabbitmqService.enviaMensagem("FILA_EMAIL", new EmailDto(dto.getEmail(), "criacao"));
        return new CadastrarClienteResponseDto("Cliente cadastrado com sucesso");
    }

}
