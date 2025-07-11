package com.barber.barber.infra.web.handlers;

import com.barber.barber.domain.exceptions.*;
import com.barber.barber.infra.web.DTOs.CadastrarAgendamentoResponseDto;
import com.barber.barber.infra.web.DTOs.CadastrarClienteResponseDto;
import com.barber.barber.infra.web.DTOs.ListarAgendamentoResponseDTO;
import com.barber.barber.infra.web.DTOs.LoginClienteResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NenhumAgendamentoEncontradoException.class)
    public ResponseEntity<ListarAgendamentoResponseDTO> handlerNenhumAgendamentoEncontrado(NenhumAgendamentoEncontradoException ex){
        logger.warn("Nenhum agendamento encontrado");
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ListarAgendamentoResponseDTO(ex.getMessage(), List.of()));
    }

    @ExceptionHandler(AgendamentoNaoEncontradoException.class)
    public ResponseEntity<CadastrarAgendamentoResponseDto> handlerAgendamentoNaoEncontrado(AgendamentoNaoEncontradoException ex){
        logger.warn(ex.getMessage());
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new CadastrarAgendamentoResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(CamposObrigatoriosException.class)
    public ResponseEntity<CadastrarAgendamentoResponseDto> handlerCamposObrigatorios(CamposObrigatoriosException ex){
        logger.warn(ex.getMessage());
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new CadastrarAgendamentoResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(AgendamentoJaExisteException.class)
    public ResponseEntity<CadastrarAgendamentoResponseDto> handlerAgendamentoJaExiste(AgendamentoJaExisteException ex){
        logger.warn(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CadastrarAgendamentoResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(AgendamentoNaoPodeSerNoPassadoException.class)
    public ResponseEntity<CadastrarAgendamentoResponseDto> handlerAgendamentoNaoPodeSerNoPassado(AgendamentoNaoPodeSerNoPassadoException ex){
        logger.warn(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CadastrarAgendamentoResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<LoginClienteResponseDTO> handlerSenhaInvalida(SenhaInvalidaException ex){
        logger.warn(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new LoginClienteResponseDTO(ex.getMessage(), ""));
    }

    @ExceptionHandler(UsuarioNaoCadastradoException.class)
    public ResponseEntity<LoginClienteResponseDTO> handlerUsuarioNaoCadastrado(UsuarioNaoCadastradoException ex){
        logger.warn(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new LoginClienteResponseDTO(ex.getMessage(), ""));
    }

    @ExceptionHandler(ContaJaCadastradaException.class)
    public ResponseEntity<CadastrarClienteResponseDto> handlerContaJaCadastrada(ContaJaCadastradaException ex){
        logger.warn(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CadastrarClienteResponseDto(ex.getMessage()));
    }
     
}
