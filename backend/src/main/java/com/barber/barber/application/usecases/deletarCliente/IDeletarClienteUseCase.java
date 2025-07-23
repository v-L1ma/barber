package com.barber.barber.application.usecases.deletarCliente;

import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;

public interface IDeletarClienteUseCase {
    CadastrarClienteResponseDto executar(int id);
}
