package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.Origem;
import br.com.formalizacaobackoffice.persistence.entity.OrigemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrigemMapper {
    public Origem converterParaModel(OrigemEntity entity) {
        return new Origem(entity.getCodigoOrigem(), entity.getNomeOrigem());
    }

    public OrigemEntity converterParaEntity(Origem model) {
        return new OrigemEntity(model.getCodigoOrigem(), model.getNomeOrigem());
    }
}
