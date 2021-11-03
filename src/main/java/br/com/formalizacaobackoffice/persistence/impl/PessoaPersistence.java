package br.com.formalizacaobackoffice.persistence.impl;

import br.com.formalizacaobackoffice.mapper.PessoaMapper;
import br.com.formalizacaobackoffice.model.Pessoa;
import br.com.formalizacaobackoffice.persistence.adapter.PessoaPersistenceAdapter;
import br.com.formalizacaobackoffice.persistence.entity.PessoaEntity;
import br.com.formalizacaobackoffice.persistence.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaPersistence implements PessoaPersistenceAdapter {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaMapper pessoaMapper;

    @Override
    public Pessoa salvarPessoa(Pessoa pessoa) {
        PessoaEntity pessoaEntity = pessoaMapper.converterParaEntity(pessoa);
        return pessoaMapper.converterParaModel(pessoaRepository.save(pessoaEntity));
    }
}
