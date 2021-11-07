package br.com.formalizacaobackoffice.exception;

import br.com.formalizacaobackoffice.exception.model.ExceptionModel;

public class SegmentacaoNotFoundException extends ExceptionModel {
    public SegmentacaoNotFoundException(String message) {
        super(message);
    }
}
