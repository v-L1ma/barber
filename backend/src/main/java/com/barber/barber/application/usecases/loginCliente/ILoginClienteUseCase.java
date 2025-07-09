package com.barber.barber.application.usecases.loginCliente;

import com.barber.barber.infra.web.DTOs.LoginClienteDTO;
import com.barber.barber.infra.web.DTOs.LoginClienteResponseDTO;

public interface ILoginClienteUseCase {
    LoginClienteResponseDTO executar(LoginClienteDTO dto);
}
