package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.persistence.adapter.TipoFormalizacaoPersistenceAdapter;
import br.com.formalizacaobackoffice.model.Origem;
import br.com.formalizacaobackoffice.model.Processo;
import br.com.formalizacaobackoffice.model.Segmentacao;
import br.com.formalizacaobackoffice.model.TipoFormalizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoFormalizacaoService {
    @Autowired
    private TipoFormalizacaoPersistenceAdapter tipoFormalizacaoPersistenceAdapter;

    public TipoFormalizacao buscarTipoFormalizacao(Processo processo, Origem origem, Segmentacao segmentacao) {
        return tipoFormalizacaoPersistenceAdapter.buscarTipoFormalizacao(processo, origem, segmentacao);
    }
}
