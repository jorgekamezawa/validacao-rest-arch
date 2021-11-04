package br.com.formalizacaobackoffice.persistence.adapter;

import br.com.formalizacaobackoffice.model.Formalizacao;

import java.util.List;

public interface FormalizacaoPersistenceAdapter {
    void salvarFormalizacao(Formalizacao formalizacao);

    Formalizacao buscarFormalizacaoPorId(String codigoFormalizacao);

    List<Formalizacao> buscarTodosFormalizacao();
}
