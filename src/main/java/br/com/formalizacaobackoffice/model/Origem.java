package br.com.formalizacaobackoffice.model;

import lombok.Getter;

@Getter
public class Origem {
    private long codigoOrigem;
    private String nomeOrigem;

    public Origem(String nomeOrigem) {
        this.nomeOrigem = nomeOrigem;
    }

    public Origem(long codigoOrigem, String nomeOrigem) {
        this.codigoOrigem = codigoOrigem;
        this.nomeOrigem = nomeOrigem;
    }
}
