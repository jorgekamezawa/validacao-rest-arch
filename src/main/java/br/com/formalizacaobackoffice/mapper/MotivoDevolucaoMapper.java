package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.MotivoDevolucao;
import br.com.formalizacaobackoffice.persistence.entity.MotivoDevolucaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MotivoDevolucaoMapper {
    @Autowired
    private ObjetoAnaliseMapper objetoAnaliseMapper;

    public List<MotivoDevolucao> converterParaModel(List<MotivoDevolucaoEntity> entityLista) {
        List<MotivoDevolucao> modelLista = new ArrayList<>();
        entityLista.forEach(entity -> {
            modelLista.add(converterParaModel(entity));
        });
        return modelLista;
    }

    public MotivoDevolucao converterParaModel(MotivoDevolucaoEntity entity) {
        return new MotivoDevolucao(
                entity.getCodigoMotivoDevolucao(),
                entity.getNomeMotivoDevolucao()
        );
    }

    public List<MotivoDevolucaoEntity> converterParaEntity(List<MotivoDevolucao> modelLista) {
        List<MotivoDevolucaoEntity> entityLista = new ArrayList<>();
        modelLista.forEach(model -> {
            entityLista.add(converterParaEntity(model));
        });
        return entityLista;
    }

    public MotivoDevolucaoEntity converterParaEntity(MotivoDevolucao model) {
        return new MotivoDevolucaoEntity(
                model.getCodigoMotivoDevolucao(),
                model.getNomeMotivoDevolucao()
        );
    }
}
