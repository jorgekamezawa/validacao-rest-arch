package br.com.formalizacaobackoffice.exception;

public class TipoFormalizacaoNotFoundException extends RuntimeException {
    public TipoFormalizacaoNotFoundException(String message) {
        super(message);
    }
}
