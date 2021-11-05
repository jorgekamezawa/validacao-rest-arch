package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.model.MotivoDevolucao;
import br.com.formalizacaobackoffice.persistence.adapter.MotivoDevolucaoPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotivoDevolucaoService {
    @Autowired
    private MotivoDevolucaoPersistenceAdapter motivoDevolucaoPersistenceAdapter;
    public MotivoDevolucao buscarMotivoDevolucaoPorNome(String nomeMotivoDevolucao) {
        return motivoDevolucaoPersistenceAdapter.buscarMotivoDevolucaoPorNome(nomeMotivoDevolucao);
    }
}
