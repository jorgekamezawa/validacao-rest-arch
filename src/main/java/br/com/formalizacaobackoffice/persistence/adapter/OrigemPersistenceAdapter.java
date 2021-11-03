package br.com.formalizacaobackoffice.persistence.adapter;

import br.com.formalizacaobackoffice.model.Origem;

public interface OrigemPersistenceAdapter {
    Origem buscarOrigem(String nomeOrigem);
}
