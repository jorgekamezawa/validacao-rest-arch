package br.com.formalizacaobackoffice.persistence.adapter;

import br.com.formalizacaobackoffice.model.Segmentacao;

public interface SegmentacaoPersistenceAdapter {
    Segmentacao buscarSegmentacao(String nomeSegmentacao);
}
