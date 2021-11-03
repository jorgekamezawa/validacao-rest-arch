package br.com.formalizacaobackoffice.persistence.impl;

import br.com.formalizacaobackoffice.exception.SegmentacaoNotFoundException;
import br.com.formalizacaobackoffice.mapper.SegmentacaoMapper;
import br.com.formalizacaobackoffice.model.Segmentacao;
import br.com.formalizacaobackoffice.persistence.adapter.SegmentacaoPersistenceAdapter;
import br.com.formalizacaobackoffice.persistence.entity.SegmentacaoEntity;
import br.com.formalizacaobackoffice.persistence.repository.SegmentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SegmentacaoPersistence implements SegmentacaoPersistenceAdapter {
    @Autowired
    private SegmentacaoRepository segmentacaoRepository;
    @Autowired
    private SegmentacaoMapper segmentacaoMapper;

    @Override
    public Segmentacao buscarSegmentacao(String nomeSegmentacao) {
        SegmentacaoEntity segmentacaoEntity = segmentacaoRepository.findByNomeSegmentacao(nomeSegmentacao)
                .orElseThrow(() -> new SegmentacaoNotFoundException("Segmentacao " + nomeSegmentacao + " nao encontrada!"));
        return segmentacaoMapper.converterParaModel(segmentacaoEntity);
    }
}
