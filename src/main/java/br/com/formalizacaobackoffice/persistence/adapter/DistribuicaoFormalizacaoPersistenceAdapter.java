package br.com.formalizacaobackoffice.persistence.adapter;

import br.com.formalizacaobackoffice.model.DistribuicaoFormalizacao;
import br.com.formalizacaobackoffice.model.TipoFormalizacao;

import java.util.List;

public interface DistribuicaoFormalizacaoPersistenceAdapter {
    List<DistribuicaoFormalizacao> buscarDistribuicaoFormalizacaoPorTipoFormalizacaoEPorcentagemMaiorQueZero(TipoFormalizacao tipoFormalizacao);

    void salvarDistribuicao(DistribuicaoFormalizacao distribuicaoSelecionada);
}
