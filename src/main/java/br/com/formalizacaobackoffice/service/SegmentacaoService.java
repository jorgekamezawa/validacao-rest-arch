package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.model.Segmentacao;
import br.com.formalizacaobackoffice.persistence.adapter.SegmentacaoPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentacaoService {
    @Autowired
    private SegmentacaoPersistenceAdapter segmentacaoPersistenceAdapter;

    public Segmentacao buscarSegmentacao(String nomeSegmentacao) {
        return segmentacaoPersistenceAdapter.buscarSegmentacao(nomeSegmentacao);
    }
}
