package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class FormalizacaoNotFoundException extends ExceptionModel {
    public FormalizacaoNotFoundException(String message) {
        super(message);
    }
}
