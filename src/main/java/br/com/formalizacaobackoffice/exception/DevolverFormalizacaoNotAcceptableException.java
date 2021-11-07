package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class DevolverFormalizacaoNotAcceptableException extends ExceptionModel {
    public DevolverFormalizacaoNotAcceptableException(String message) {
        super(message);
    }
}
