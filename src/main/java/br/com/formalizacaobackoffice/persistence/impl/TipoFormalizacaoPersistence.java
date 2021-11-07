package br.com.formalizacaobackoffice.persistence.impl;

import br.com.formalizacaobackoffice.exception.TipoFormalizacaoNotFoundException;
import br.com.formalizacaobackoffice.mapper.OrigemMapper;
import br.com.formalizacaobackoffice.mapper.ProcessoMapper;
import br.com.formalizacaobackoffice.mapper.SegmentacaoMapper;
import br.com.formalizacaobackoffice.mapper.TipoFormalizacaoMapper;
import br.com.formalizacaobackoffice.model.Origem;
import br.com.formalizacaobackoffice.model.Processo;
import br.com.formalizacaobackoffice.model.Segmentacao;
import br.com.formalizacaobackoffice.model.TipoFormalizacao;
import br.com.formalizacaobackoffice.persistence.adapter.TipoFormalizacaoPersistenceAdapter;
import br.com.formalizacaobackoffice.persistence.entity.OrigemEntity;
import br.com.formalizacaobackoffice.persistence.entity.ProcessoEntity;
import br.com.formalizacaobackoffice.persistence.entity.SegmentacaoEntity;
import br.com.formalizacaobackoffice.persistence.entity.TipoFormalizacaoEntity;
import br.com.formalizacaobackoffice.persistence.repository.TipoFormalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TipoFormalizacaoPersistence implements TipoFormalizacaoPersistenceAdapter {
    @Autowired
    private TipoFormalizacaoRepository tipoFormalizacaoRepository;
    @Autowired
    private TipoFormalizacaoMapper tipoFormalizacaoMapper;
    @Autowired
    private ProcessoMapper processoMapper;
    @Autowired
    private OrigemMapper origemMapper;
    @Autowired
    private SegmentacaoMapper segmentacaoMapper;

    @Override
    public TipoFormalizacao buscarTipoFormalizacao(Processo processo, Origem origem, Segmentacao segmentacao) {
        ProcessoEntity processoEntity = processoMapper.converterParaEntity(processo);
        OrigemEntity origemEntity = origemMapper.converterParaEntity(origem);
        SegmentacaoEntity segmentacaoEntity = segmentacaoMapper.converterParaEntity(segmentacao);

        TipoFormalizacaoEntity tipoFormalizacaoEntity = tipoFormalizacaoRepository.findByProcessoEntityAndOrigemEntityAndSegmentacaoEntity(processoEntity, origemEntity, segmentacaoEntity)
                .orElseThrow(() -> new TipoFormalizacaoNotFoundException("Tipo formalizacao de processo " + processo.getNomeProcesso() +
                        ", origem " + origem.getNomeOrigem() + " e segmentacao " + segmentacao.getNomeSegmentacao() + " nao encontrado!"));
        return tipoFormalizacaoMapper.converterParaModel(tipoFormalizacaoEntity);
    }
}
