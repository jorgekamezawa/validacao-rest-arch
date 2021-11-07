package br.com.formalizacaobackoffice.exception.handler;

import br.com.formalizacaobackoffice.exception.*;
import br.com.formalizacaobackoffice.exception.model.ErrorModel;
import br.com.formalizacaobackoffice.exception.model.ExceptionModel;
import br.com.formalizacaobackoffice.util.FormatadorDeDataUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomHandlerException {

    @ExceptionHandler(value = {FormalizacaoNotFoundException.class, MotivoDevolucaoNotFoundException.class, OrigemNotFoundException.class,
            ProcessoNotFoundExeption.class, SegmentacaoNotFoundException.class, TipoFormalizacaoNotFoundException.class,
            DistribuicaoNotFoundException.class, EfetivarNotFoundException.class})
    public ResponseEntity<ErrorModel> handlerNotFoundException(ExceptionModel ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .errors(ex.getCause())
                .localDateTime(FormatadorDeDataUtil.formatarDataHora(LocalDateTime.now())).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {DevolverFormalizacaoNotAcceptableException.class, DistribuicaoNotAcceptableException.class,
            EfetivarNotAcceptableException.class, ObjetoAnaliseFormalizacaoNotAcceptableException.class})
    public ResponseEntity<ErrorModel> handlerNotAcceptableException(ExceptionModel ex) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .errors(ex.getCause())
                .localDateTime(FormatadorDeDataUtil.formatarDataHora(LocalDateTime.now())).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {DistribuicaoInternalServerErrorException.class})
    public ResponseEntity<ErrorModel> handlerInternalServerErrorException(ExceptionModel ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .errors(ex.getCause())
                .localDateTime(FormatadorDeDataUtil.formatarDataHora(LocalDateTime.now())).build();

        return new ResponseEntity<>(errorModel, status);
    }

    @ExceptionHandler(value = {FormalizadorRestClientException.class})
    public ResponseEntity<ErrorModel> handlerRestClientException(ExceptionModel ex) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;

        ErrorModel errorModel = ErrorModel.builder()
                .message(ex.getMessage())
                .status(status)
                .errors(ex.getCause())
                .localDateTime(FormatadorDeDataUtil.formatarDataHora(LocalDateTime.now())).build();

        return new ResponseEntity<>(errorModel, status);
    }
}
