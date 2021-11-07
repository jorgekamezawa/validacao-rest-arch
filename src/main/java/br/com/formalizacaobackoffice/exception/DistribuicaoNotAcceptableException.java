package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class DistribuicaoNotAcceptableException extends ExceptionModel {
    public DistribuicaoNotAcceptableException(String message) {
        super(message);
    }
}
