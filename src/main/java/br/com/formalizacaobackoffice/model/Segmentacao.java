package br.com.formalizacaobackoffice.model;

import lombok.Getter;

@Getter
public class Segmentacao {
    private long codigoSegmentacao;
    private String nomeSegmentacao;

    public Segmentacao(String nomeSegmentacao) {
        this.nomeSegmentacao = nomeSegmentacao;
    }

    public Segmentacao(long codigoSegmentacao, String nomeSegmentacao) {
        this.codigoSegmentacao = codigoSegmentacao;
        this.nomeSegmentacao = nomeSegmentacao;
    }
}
