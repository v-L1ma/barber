package com.barber.barber.infra.web.DTOs;

import com.barber.barber.domain.entities.Cliente.Cliente;

public class LoginClienteResponseDTO {

    String message;
    String token;
    Cliente clienteInfo;

    public LoginClienteResponseDTO(String message, String token, Cliente clienteInfo) {
        this.message = message;
        this.token = token;
        this.clienteInfo = clienteInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Cliente getClienteInfo() {
        return clienteInfo;
    }

    public void setClienteInfo(Cliente clienteInfo) {
        this.clienteInfo = clienteInfo;
    }
}
