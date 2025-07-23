package com.barber.barber.application.services.TokenService;

import com.barber.barber.domain.entities.Cliente.Cliente;

import java.time.Instant;

public interface ITokenService {
    String generateToken(Cliente cliente);
    Instant generateExpirationDate();
    String validateToken(String token);
}
