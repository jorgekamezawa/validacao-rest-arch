package br.com.formalizacaobackoffice.model;

import lombok.Getter;

import java.util.Comparator;

@Getter
public class Segmentacao implements Comparable<Segmentacao> {
    private long codigoSegmentacao;
    private String nomeSegmentacao;

    public Segmentacao(String nomeSegmentacao) {
        this.nomeSegmentacao = nomeSegmentacao;
    }

    public Segmentacao(long codigoSegmentacao, String nomeSegmentacao) {
        this.codigoSegmentacao = codigoSegmentacao;
        this.nomeSegmentacao = nomeSegmentacao;
    }

    @Override
    public int compareTo(Segmentacao segmentacao) {
        return this.getNomeSegmentacao().compareTo(segmentacao.getNomeSegmentacao());
    }
}
