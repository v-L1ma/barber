package com.barber.barber.application.usecases.deletarCliente;

import com.barber.barber.application.services.ClienteService.IClienteService;
import com.barber.barber.domain.entities.Cliente.Cliente;
import com.barber.barber.domain.exceptions.UsuarioNaoCadastradoException;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;
import org.springframework.stereotype.Service;

@Service
public class deletarClienteUseCase implements IDeletarClienteUseCase{

    private final IClienteService clienteService;

    public deletarClienteUseCase(IClienteService clienteService){
        this.clienteService = clienteService;
    }

    @Override
    public CadastrarClienteResponseDto executar(int id) {
        Cliente cliente = this.clienteService.buscarClientePorId(id);

        if (cliente==null){
            throw new UsuarioNaoCadastradoException();
        }

        this.clienteService.deletarCliente(id);

        return new CadastrarClienteResponseDto("Usuario deletado com sucesso");
    }
}
