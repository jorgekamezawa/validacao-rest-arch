package br.com.formalizacaobackoffice.persistence.adapter;

import br.com.formalizacaobackoffice.model.Formalizacao;

public interface FormalizacaoPersistenceAdapter {
    void salvarFormalizacao(Formalizacao formalizacao);

    Formalizacao buscarFormalizacaoPorId(String codigoFormalizacao);
}
