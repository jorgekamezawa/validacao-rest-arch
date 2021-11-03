package br.com.formalizacaobackoffice.exception;

public class ProcessoNotFoundExeption extends RuntimeException {
    public ProcessoNotFoundExeption(String message) {
        super(message);
    }
}
