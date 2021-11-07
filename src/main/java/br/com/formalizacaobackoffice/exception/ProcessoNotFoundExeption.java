package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class ProcessoNotFoundExeption extends ExceptionModel {
    public ProcessoNotFoundExeption(String message) {
        super(message);
    }
}
