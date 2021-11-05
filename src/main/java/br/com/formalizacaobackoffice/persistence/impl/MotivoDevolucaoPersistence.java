package br.com.formalizacaobackoffice.persistence.impl;

import br.com.formalizacaobackoffice.exception.MotivoDevolucaoNotFoundException;
import br.com.formalizacaobackoffice.mapper.MotivoDevolucaoMapper;
import br.com.formalizacaobackoffice.model.MotivoDevolucao;
import br.com.formalizacaobackoffice.persistence.adapter.MotivoDevolucaoPersistenceAdapter;
import br.com.formalizacaobackoffice.persistence.entity.MotivoDevolucaoEntity;
import br.com.formalizacaobackoffice.persistence.repository.MotivoDevolucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MotivoDevolucaoPersistence implements MotivoDevolucaoPersistenceAdapter {
    @Autowired
    private MotivoDevolucaoRepository motivoDevolucaoRepository;
    @Autowired
    private MotivoDevolucaoMapper motivoDevolucaoMapper;

    @Override
    public MotivoDevolucao buscarMotivoDevolucaoPorNome(String nomeMotivoDevolucao) {
        MotivoDevolucaoEntity motivoDevolucaoEntity = motivoDevolucaoRepository.findByNomeMotivoDevolucao(nomeMotivoDevolucao)
                .orElseThrow(() -> new MotivoDevolucaoNotFoundException("Motivo Devolucao nao encontrado pelo nome " + nomeMotivoDevolucao));
        return motivoDevolucaoMapper.converterParaModel(motivoDevolucaoEntity);
    }
}
