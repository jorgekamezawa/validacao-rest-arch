package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class MotivoDevolucaoNotFoundException extends ExceptionModel {
    public MotivoDevolucaoNotFoundException(String message) {
        super(message);
    }
}
