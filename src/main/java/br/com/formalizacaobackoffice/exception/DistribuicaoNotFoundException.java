package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class DistribuicaoNotFoundException extends ExceptionModel {
    public DistribuicaoNotFoundException(String message) {
        super(message);
    }
}
