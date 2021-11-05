package br.com.formalizacaobackoffice.persistence.adapter;

import br.com.formalizacaobackoffice.model.MotivoDevolucao;

public interface MotivoDevolucaoPersistenceAdapter {
    MotivoDevolucao buscarMotivoDevolucaoPorNome(String nomeMotivoDevolucao);
}
