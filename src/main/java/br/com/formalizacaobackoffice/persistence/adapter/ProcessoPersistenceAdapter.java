package br.com.formalizacaobackoffice.persistence.adapter;

import br.com.formalizacaobackoffice.model.Processo;

public interface ProcessoPersistenceAdapter {
    Processo buscarProcessoProNome(String nomeProcesso);
}
