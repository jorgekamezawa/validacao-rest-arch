package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class FormalizadorRestClientException extends ExceptionModel {
    public FormalizadorRestClientException(String message) {
        super(message);
    }
}
