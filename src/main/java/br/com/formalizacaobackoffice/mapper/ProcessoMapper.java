package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.Processo;
import br.com.formalizacaobackoffice.persistence.entity.ProcessoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProcessoMapper {
    public Processo converterParaModel(ProcessoEntity entity) {
        return new Processo(entity.getCodigoProcesso(), entity.getNomeProcesso());
    }

    public ProcessoEntity converterParaEntity(Processo model) {
        return new ProcessoEntity(model.getCodigoProcesso(), model.getNomeProcesso());
    }
}
