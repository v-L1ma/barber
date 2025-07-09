package com.barber.barber.application.usecases.cadastrarCliente;

import com.barber.barber.application.services.ClienteService.IClienteService;
import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.domain.exceptions.AgendamentoJaExisteException;
import com.barber.barber.domain.exceptions.AgendamentoNaoPodeSerNoPassadoException;
import com.barber.barber.domain.exceptions.CamposObrigatoriosException;
import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CadastrarClienteUseCase implements ICadastrarClienteUseCase{

    private final IClienteService clienteService;

    public CadastrarClienteUseCase(IClienteService clienteService){
        this.clienteService = clienteService;
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
            throw new AgendamentoNaoPodeSerNoPassadoException();
        }

        Cliente isClienteJaCadastrado = clienteService.buscarClientePorEmail(dto.getEmail());

        if (isClienteJaCadastrado!=null){
            throw new AgendamentoJaExisteException();
            //criar contaJaCadastradaException
        }

        clienteService.cadastrarCliente(dto);
        return new CadastrarClienteResponseDto("Cliente cadastrado com sucesso");
    }

}
