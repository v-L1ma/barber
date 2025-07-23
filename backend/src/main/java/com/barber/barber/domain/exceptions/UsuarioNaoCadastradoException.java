package com.barber.barber.domain.exceptions;

public class UsuarioNaoCadastradoException extends RuntimeException {
    public UsuarioNaoCadastradoException(String message) {
        super(message);
    }

    public UsuarioNaoCadastradoException() {
        super("Não existe nenhum usuário cadastrado com esse email.");
    }
}
