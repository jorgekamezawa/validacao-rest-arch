package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class OrigemNotFoundException extends ExceptionModel {
    public OrigemNotFoundException(String message) {
        super(message);
    }
}
