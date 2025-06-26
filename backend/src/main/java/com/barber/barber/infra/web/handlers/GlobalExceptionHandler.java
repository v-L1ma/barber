package com.barber.barber.infra.web.handlers;

import com.barber.barber.domain.exceptions.NenhumAgendamentoEncontradoException;
import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<ListarAgendamentoResponseDTO> handlerNenhumAgendamentoEncontrado(NenhumAgendamentoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListarAgendamentoResponseDTO(ex.getMessage(), List.of()));
    }
}
