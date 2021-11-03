package br.com.formalizacaobackoffice.persistence.adapter;

import br.com.formalizacaobackoffice.model.Pessoa;

public interface PessoaPersistenceAdapter {
    Pessoa salvarPessoa(Pessoa pessoa);
}
