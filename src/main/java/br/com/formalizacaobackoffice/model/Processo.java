package br.com.formalizacaobackoffice.model;

import lombok.Getter;

@Getter
public class Processo {
    private long codigoProcesso;
    private String nomeProcesso;

    public Processo(String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
    }

    public Processo(long codigoProcesso, String nomeProcesso) {
        this.codigoProcesso = codigoProcesso;
        this.nomeProcesso = nomeProcesso;
    }
}
