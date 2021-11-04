package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.dto.TipoFormalizacaoDto;
import br.com.formalizacaobackoffice.model.*;
import br.com.formalizacaobackoffice.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TipoFormalizacaoMapper {
    @Autowired
    private ProcessoMapper processoMapper;
    @Autowired
    private OrigemMapper origemMapper;
    @Autowired
    private SegmentacaoMapper segmentacaoMapper;
    @Autowired
    private ObjetoAnaliseMapper objetoAnaliseMapper;

    public List<TipoFormalizacao> converterParaModel(List<TipoFormalizacaoEntity> entityLista) {
        List<TipoFormalizacao> modelLista = new ArrayList<>();
        entityLista.forEach(entity -> {
            modelLista.add(converterParaModel(entity));
        });
        return modelLista;
    }

    public TipoFormalizacao converterParaModel(TipoFormalizacaoEntity entity) {
        Processo processoModel = processoMapper.converterParaModel(entity.getProcessoEntity());
        Origem origemModel = origemMapper.converterParaModel(entity.getOrigemEntity());
        Segmentacao segmentacaoModel = segmentacaoMapper.converterParaModel(entity.getSegmentacaoEntity());
        List<ObjetoAnalise> objetoAnaliseModelLista = objetoAnaliseMapper.converterParaModel(entity.getObjetoAnaliseEntityLista());
        return new TipoFormalizacao(
                entity.getCodigoTipoFormalizacao(),
                processoModel,
                origemModel,
                segmentacaoModel,
                objetoAnaliseModelLista);
    }

    public List<TipoFormalizacaoEntity> converterParaEntity(List<TipoFormalizacao> modelLista) {
        List<TipoFormalizacaoEntity> entityLista = new ArrayList<>();
        modelLista.forEach(model -> {
            entityLista.add(converterParaEntity(model));
        });
        return entityLista;
    }

    public TipoFormalizacaoEntity converterParaEntity(TipoFormalizacao model) {
        ProcessoEntity processoEntity = processoMapper.converterParaEntity(model.getProcesso());
        OrigemEntity origemEntity = origemMapper.converterParaEntity(model.getOrigem());
        SegmentacaoEntity segmentacaoEntity = segmentacaoMapper.converterParaEntity(model.getSegmentacao());
        List<ObjetoAnaliseEntity> objetoAnaliseEntityLista = objetoAnaliseMapper.converterParaEntity(model.getObjetoAnaliseLista());
        return new TipoFormalizacaoEntity(
                model.getCodigoTipoFormalizacao(),
                processoEntity,
                origemEntity,
                segmentacaoEntity,
                objetoAnaliseEntityLista
        );
    }

    public TipoFormalizacaoDto converterParaDto(TipoFormalizacao model) {
        return new TipoFormalizacaoDto(
                model.getProcesso().getNomeProcesso(),
                model.getOrigem().getNomeOrigem(),
                model.getSegmentacao().getNomeSegmentacao()
        );
    }
}
