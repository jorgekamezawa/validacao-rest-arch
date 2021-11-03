package br.com.formalizacaobackoffice.exception;

public class FormalizacaoNotFoundException extends RuntimeException {
    public FormalizacaoNotFoundException(String message) {
        super(message);
    }
}
