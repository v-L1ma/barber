package com.barber.barber.application.usecases.atualizarDadosCadastrais;

import com.barber.barber.infra.web.DTOs.CadastrarClienteDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;

public interface IAtualizarDadosCadastraisUseCase {
    CadastrarClienteResponseDto executar(int id, CadastrarClienteDto dto);
}
