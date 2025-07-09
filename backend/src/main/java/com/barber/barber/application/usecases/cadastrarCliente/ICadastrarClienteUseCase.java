package com.barber.barber.application.usecases.cadastrarCliente;

import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;

public interface ICadastrarClienteUseCase {

 CadastrarClienteResponseDto executar(CadastrarClienteDto dto);

}
