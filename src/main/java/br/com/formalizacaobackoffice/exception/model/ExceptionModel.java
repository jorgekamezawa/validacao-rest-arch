package br.com.formalizacaobackoffice.exception.model;

public class ExceptionModel extends RuntimeException {
    public ExceptionModel(String message) {
        super(message);
    }
}
