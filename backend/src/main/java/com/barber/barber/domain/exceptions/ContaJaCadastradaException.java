package com.barber.barber.domain.exceptions;

public class ContaJaCadastradaException extends RuntimeException {
    public ContaJaCadastradaException(String message) {
        super(message);
    }

  public ContaJaCadastradaException() {
    super("Já existe uma conta cadastrada com esse email.");
  }
}
