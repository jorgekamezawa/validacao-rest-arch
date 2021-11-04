package br.com.formalizacaobackoffice.model;

import lombok.Getter;

@Getter
public class Processo implements Comparable<Processo> {
    private long codigoProcesso;
    private String nomeProcesso;

    public Processo(String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
    }

    public Processo(long codigoProcesso, String nomeProcesso) {
        this.codigoProcesso = codigoProcesso;
        this.nomeProcesso = nomeProcesso;
    }

    @Override
    public int compareTo(Processo processo) {
        return this.getNomeProcesso().compareTo(processo.getNomeProcesso());
    }

//    @Override
//    public int compare(Processo p1, Processo p2) {
//        return p1.getNomeProcesso().compareTo(p2.getNomeProcesso());
//    }
}
