package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class EfetivarNotFoundException extends ExceptionModel {
    public EfetivarNotFoundException(String message) {
        super(message);
    }
}
