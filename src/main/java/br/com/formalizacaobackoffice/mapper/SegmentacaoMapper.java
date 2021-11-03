package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.Segmentacao;
import br.com.formalizacaobackoffice.persistence.entity.SegmentacaoEntity;
import org.springframework.stereotype.Component;

@Component
public class SegmentacaoMapper {
    public Segmentacao converterParaModel(SegmentacaoEntity entity) {
        return new Segmentacao(entity.getCodigoSegmentacao(), entity.getNomeSegmentacao());
    }

    public SegmentacaoEntity converterParaEntity(Segmentacao model) {
        return new SegmentacaoEntity(model.getCodigoSegmentacao(), model.getNomeSegmentacao());
    }
}
