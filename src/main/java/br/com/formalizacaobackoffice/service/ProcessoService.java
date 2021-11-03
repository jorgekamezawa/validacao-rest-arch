package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.model.Processo;
import br.com.formalizacaobackoffice.persistence.adapter.ProcessoPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoPersistenceAdapter processoPersistenceAdapter;

    public Processo buscarProcesso(String nomeProcesso) {
        return processoPersistenceAdapter.buscarProcessoProNome(nomeProcesso);
    }
}
