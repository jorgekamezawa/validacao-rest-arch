package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class DistribuicaoInternalServerErrorException extends ExceptionModel {
    public DistribuicaoInternalServerErrorException(String message) {
        super(message);
    }
}
