package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.model.Origem;
import br.com.formalizacaobackoffice.persistence.adapter.OrigemPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrigemService {
    @Autowired
    private OrigemPersistenceAdapter origemPersistenceAdapter;

    public Origem buscarOrigem(String nomeOrigem) {
        return origemPersistenceAdapter.buscarOrigem(nomeOrigem);
    }
}
