package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.model.Pessoa;
import br.com.formalizacaobackoffice.persistence.adapter.PessoaPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    @Autowired
    private PessoaPersistenceAdapter pessoaPersistenceAdapter;
    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaPersistenceAdapter.salvarPessoa(pessoa);
    }
}
