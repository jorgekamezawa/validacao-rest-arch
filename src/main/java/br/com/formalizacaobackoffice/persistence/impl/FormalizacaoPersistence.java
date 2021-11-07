package br.com.formalizacaobackoffice.persistence.impl;

import br.com.formalizacaobackoffice.exception.FormalizacaoNotFoundException;
import br.com.formalizacaobackoffice.mapper.FormalizacaoMapper;
import br.com.formalizacaobackoffice.mapper.ObjetoAnaliseFormalizacaoMapper;
import br.com.formalizacaobackoffice.model.Formalizacao;
import br.com.formalizacaobackoffice.persistence.adapter.FormalizacaoPersistenceAdapter;
import br.com.formalizacaobackoffice.persistence.entity.FormalizacaoEntity;
import br.com.formalizacaobackoffice.persistence.repository.FormalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FormalizacaoPersistence implements FormalizacaoPersistenceAdapter {
    @Autowired
    private FormalizacaoRepository formalizacaoRepository;
    @Autowired
    private FormalizacaoMapper formalizacaoMapper;
    @Autowired
    private ObjetoAnaliseFormalizacaoMapper objetoAnaliseFormalizacaoMapper;

    @Override
    @Transactional
    public void salvarFormalizacao(Formalizacao formalizacao) {
        FormalizacaoEntity formalizacaoEntity = formalizacaoMapper.converterParaEntity(formalizacao);
        formalizacaoEntity.adicionarObjetoAnaliseFormalizacao(objetoAnaliseFormalizacaoMapper.converterParaEntity(formalizacao.getObjetoAnaliseFormalizacaoLista()));
        formalizacaoRepository.save(formalizacaoEntity);
    }

    @Override
    public Formalizacao buscarFormalizacaoPorId(String codigoFormalizacao) {
        FormalizacaoEntity formalizacaoEntity = formalizacaoRepository.findById(codigoFormalizacao)
                .orElseThrow(() -> new FormalizacaoNotFoundException("Nao foi encontrada formalizacao com o id: " + codigoFormalizacao));
        return formalizacaoMapper.converterParaModel(formalizacaoEntity);
    }
}
