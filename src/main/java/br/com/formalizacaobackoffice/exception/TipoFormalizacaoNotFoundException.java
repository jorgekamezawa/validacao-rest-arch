package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class TipoFormalizacaoNotFoundException extends ExceptionModel {
    public TipoFormalizacaoNotFoundException(String message) {
        super(message);
    }
}
