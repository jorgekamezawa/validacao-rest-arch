package br.com.formalizacaobackoffice.persistence.impl;

import br.com.formalizacaobackoffice.persistence.adapter.PessoaPersistenceAdapter;
import br.com.formalizacaobackoffice.persistence.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaPersistence implements PessoaPersistenceAdapter {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public boolean validarSePessoaExiste(String codigoPessoa) {
        return pessoaRepository.findById(codigoPessoa).isPresent();
    }
}
