package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.model.Origem;
import br.com.formalizacaobackoffice.model.Processo;
import br.com.formalizacaobackoffice.model.Segmentacao;
import br.com.formalizacaobackoffice.model.TipoFormalizacao;
import br.com.formalizacaobackoffice.persistence.adapter.TipoFormalizacaoPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoFormalizacaoService {
    @Autowired
    private ProcessoService processoService;
    @Autowired
    private OrigemService origemService;
    @Autowired
    private SegmentacaoService segmentacaoService;
    @Autowired
    private TipoFormalizacaoPersistenceAdapter tipoFormalizacaoPersistenceAdapter;

    public TipoFormalizacao buscarTipoFormalizacaoPelosNomesDasCategorias(String nomeProcesso, String nomeOrigem, String nomeSegmentacao) {
        Processo processo = processoService.buscarProcesso(nomeProcesso);
        Origem origem = origemService.buscarOrigem(nomeOrigem);
        Segmentacao segmentacao = segmentacaoService.buscarSegmentacao(nomeSegmentacao);

        return buscarTipoFormalizacao(processo, origem, segmentacao);
    }

    private TipoFormalizacao buscarTipoFormalizacao(Processo processo, Origem origem, Segmentacao segmentacao) {
        return tipoFormalizacaoPersistenceAdapter.buscarTipoFormalizacao(processo, origem, segmentacao);
    }
}
