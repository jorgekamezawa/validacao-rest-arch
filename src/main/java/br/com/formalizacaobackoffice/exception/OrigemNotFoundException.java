package br.com.formalizacaobackoffice.exception;

public class OrigemNotFoundException extends RuntimeException {
    public OrigemNotFoundException(String message) {
        super(message);
    }
}
