package br.com.formalizacaobackoffice.persistence.adapter;

import br.com.formalizacaobackoffice.model.Origem;
import br.com.formalizacaobackoffice.model.Processo;
import br.com.formalizacaobackoffice.model.Segmentacao;
import br.com.formalizacaobackoffice.model.TipoFormalizacao;

public interface TipoFormalizacaoPersistenceAdapter {
    TipoFormalizacao buscarTipoFormalizacao(Processo processo, Origem origem, Segmentacao segmentacao);
}
