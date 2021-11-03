package br.com.formalizacaobackoffice.persistence.impl;

import br.com.formalizacaobackoffice.exception.OrigemNotFoundException;
import br.com.formalizacaobackoffice.mapper.OrigemMapper;
import br.com.formalizacaobackoffice.model.Origem;
import br.com.formalizacaobackoffice.persistence.adapter.OrigemPersistenceAdapter;
import br.com.formalizacaobackoffice.persistence.entity.OrigemEntity;
import br.com.formalizacaobackoffice.persistence.repository.OrigemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrigemPersistence implements OrigemPersistenceAdapter {
    @Autowired
    private OrigemRepository origemRepository;
    @Autowired
    private OrigemMapper origemMapper;

    @Override
    public Origem buscarOrigem(String nomeOrigem) {
        OrigemEntity origemEntity = origemRepository.findByNomeOrigem(nomeOrigem)
                .orElseThrow(() -> new OrigemNotFoundException("Origem " + nomeOrigem + " nao encontrada!"));
        return origemMapper.converterParaModel(origemEntity);
    }
}
