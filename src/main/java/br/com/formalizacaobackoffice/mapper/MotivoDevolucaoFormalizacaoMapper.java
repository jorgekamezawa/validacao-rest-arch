package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.MotivoDevolucao;
import br.com.formalizacaobackoffice.model.MotivoDevolucaoFormalizacao;
import br.com.formalizacaobackoffice.model.ObjetoAnaliseFormalizacao;
import br.com.formalizacaobackoffice.persistence.entity.MotivoDevolucaoEntity;
import br.com.formalizacaobackoffice.persistence.entity.MotivoDevolucaoFormalizacaoEntity;
import br.com.formalizacaobackoffice.persistence.entity.ObjetoAnaliseFormalizacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MotivoDevolucaoFormalizacaoMapper {
    @Autowired
    private MotivoDevolucaoMapper motivoDevolucaoMapper;
    @Autowired
    private ObjetoAnaliseFormalizacaoMapper objetoAnaliseFormalizacaoMapper;

    public List<MotivoDevolucaoFormalizacaoEntity> converterParaEntity(List<MotivoDevolucaoFormalizacao> modelLista) {
        List<MotivoDevolucaoFormalizacaoEntity> entityLista = new ArrayList<>();
        modelLista.forEach(model -> {
            entityLista.add(converterParaEntity(model));
        });
        return entityLista;
    }

    public MotivoDevolucaoFormalizacaoEntity converterParaEntity(MotivoDevolucaoFormalizacao model) {
        MotivoDevolucaoEntity motivoDevolucaoEntity = motivoDevolucaoMapper.converterParaEntity(model.getMotivoDevolucao());
        ObjetoAnaliseFormalizacaoEntity objetoAnaliseFormalizacaoEntity = objetoAnaliseFormalizacaoMapper.converterParaEntity(model.getObjetoAnaliseFormalizacao());
        return new MotivoDevolucaoFormalizacaoEntity(
                model.getCodigoMotivoDevolucaoFormalizacao(),
                motivoDevolucaoEntity,
                model.getStatus(),
                objetoAnaliseFormalizacaoEntity
        );
    }

    public List<MotivoDevolucaoFormalizacao> converterParaModel(List<MotivoDevolucaoFormalizacaoEntity> entityLista) {
        List<MotivoDevolucaoFormalizacao> modelList = new ArrayList<>();
        entityLista.forEach(entity -> {
            modelList.add(converterParaModel(entity));
        });
        return modelList;
    }

    public MotivoDevolucaoFormalizacao converterParaModel(MotivoDevolucaoFormalizacaoEntity entity) {
        MotivoDevolucao motivoDevolucaoModel = motivoDevolucaoMapper.converterParaModel(entity.getMotivoDevolucaoEntity());
        ObjetoAnaliseFormalizacao objetoAnaliseFormalizacaoModel = objetoAnaliseFormalizacaoMapper.converterParaModel(entity.getObjetoAnaliseFormalizacaoEntity());
        return new MotivoDevolucaoFormalizacao(
                entity.getCodigoMotivoDevolucaoFormalizacao(),
                motivoDevolucaoModel,
                entity.getStatus(),
                objetoAnaliseFormalizacaoModel
        );
    }
}
