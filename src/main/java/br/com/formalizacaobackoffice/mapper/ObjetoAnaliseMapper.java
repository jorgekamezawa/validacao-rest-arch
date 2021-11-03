package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.MotivoDevolucao;
import br.com.formalizacaobackoffice.model.ObjetoAnalise;
import br.com.formalizacaobackoffice.persistence.entity.MotivoDevolucaoEntity;
import br.com.formalizacaobackoffice.persistence.entity.ObjetoAnaliseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObjetoAnaliseMapper {
    @Autowired
    private MotivoDevolucaoMapper motivoDevolucaoMapper;
    @Autowired
    private TipoFormalizacaoMapper tipoFormalizacaoMapper;

    public List<ObjetoAnalise> converterParaModel(List<ObjetoAnaliseEntity> entityLista) {
        List<ObjetoAnalise> modelLista = new ArrayList<>();
        entityLista.forEach(entity -> {
            modelLista.add(converterParaModel(entity));
        });
        return modelLista;
    }

    public ObjetoAnalise converterParaModel(ObjetoAnaliseEntity entity) {
        List<MotivoDevolucao> motivoDevolucaoModelLista = motivoDevolucaoMapper.converterParaModel(entity.getMotivoDevolucaoEntityLista());
        return new ObjetoAnalise(
                entity.getCodigoObjetoAnalise(),
                entity.getNomeObjetoAnalise(),
                motivoDevolucaoModelLista
        );
    }

    public List<ObjetoAnaliseEntity> converterParaEntity(List<ObjetoAnalise> modelLista) {
        List<ObjetoAnaliseEntity> entityLista = new ArrayList<>();
        modelLista.forEach(model -> {
            entityLista.add(converterParaEntity(model));
        });
        return entityLista;
    }

    public ObjetoAnaliseEntity converterParaEntity(ObjetoAnalise model) {
        List<MotivoDevolucaoEntity> motivoDevolucaoEntityLista = motivoDevolucaoMapper.converterParaEntity(model.getMotivoDevolucaoLista());
        return new ObjetoAnaliseEntity(
                model.getCodigoObjetoAnalise(),
                model.getNomeObjetoAnalise(),
                motivoDevolucaoEntityLista
        );
    }
}
