package br.com.formalizacaobackoffice.exception.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorModel {
    private final HttpStatus status;
    private final String message;
    private final Throwable errors;
    private final String localDateTime;
}
