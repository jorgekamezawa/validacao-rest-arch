package br.com.formalizacaobackoffice.persistence.impl;

import br.com.formalizacaobackoffice.exception.ProcessoNotFoundExeption;
import br.com.formalizacaobackoffice.mapper.ProcessoMapper;
import br.com.formalizacaobackoffice.model.Processo;
import br.com.formalizacaobackoffice.persistence.adapter.ProcessoPersistenceAdapter;
import br.com.formalizacaobackoffice.persistence.entity.ProcessoEntity;
import br.com.formalizacaobackoffice.persistence.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessoPersistence implements ProcessoPersistenceAdapter {
    @Autowired
    private ProcessoRepository processoRepository;
    @Autowired
    private ProcessoMapper processoMapper;

    @Override
    public Processo buscarProcessoProNome(String nomeProcesso) {
        ProcessoEntity processoEntity = processoRepository.findByNomeProcesso(nomeProcesso)
                .orElseThrow(() -> new ProcessoNotFoundExeption("Processo " + nomeProcesso + " nao encontrado!"));
        return processoMapper.converterParaModel(processoEntity);
    }
}
