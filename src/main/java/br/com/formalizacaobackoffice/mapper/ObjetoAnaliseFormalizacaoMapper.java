package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.MotivoDevolucaoFormalizacao;
import br.com.formalizacaobackoffice.model.ObjetoAnalise;
import br.com.formalizacaobackoffice.model.ObjetoAnaliseFormalizacao;
import br.com.formalizacaobackoffice.persistence.entity.ObjetoAnaliseEntity;
import br.com.formalizacaobackoffice.persistence.entity.ObjetoAnaliseFormalizacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObjetoAnaliseFormalizacaoMapper {
    @Autowired
    private FormalizacaoMapper formalizacaoMapper;
    @Autowired
    private ObjetoAnaliseMapper objetoAnaliseMapper;
    @Autowired
    private MotivoDevolucaoFormalizacaoMapper motivoDevolucaoFormalizacaoMapper;

    public List<ObjetoAnaliseFormalizacaoEntity> converterParaEntity(List<ObjetoAnaliseFormalizacao> modelLista) {
        List<ObjetoAnaliseFormalizacaoEntity> entityLista = new ArrayList<>();
        modelLista.forEach(model -> {
            entityLista.add(converterParaEntity(model));
        });
        return entityLista;
    }

    public ObjetoAnaliseFormalizacaoEntity converterParaEntity(ObjetoAnaliseFormalizacao model) {
        ObjetoAnaliseEntity objetoAnaliseEntity = objetoAnaliseMapper.converterParaEntity(model.getObjetoAnalise());
        return new ObjetoAnaliseFormalizacaoEntity(
                objetoAnaliseEntity,
                model.getStatusAnalise(),
                model.getCodigoImagem()
        );
    }

    public List<ObjetoAnaliseFormalizacao> converterParaModel(List<ObjetoAnaliseFormalizacaoEntity> entityLista) {
        List<ObjetoAnaliseFormalizacao> modelLista = new ArrayList<>();
        entityLista.forEach(entity -> {
            modelLista.add(converterParaModel(entity));
        });
        return modelLista;
    }

    public ObjetoAnaliseFormalizacao converterParaModel(ObjetoAnaliseFormalizacaoEntity entity) {
        ObjetoAnalise objetoAnaliseModel = objetoAnaliseMapper.converterParaModel(entity.getObjetoAnaliseEntity());
        return new ObjetoAnaliseFormalizacao(
                entity.getCodigoObjetoAnaliseFormalizacao(),
                entity.getFormalizacaoEntity().getCodigoFormalizacao(),
                objetoAnaliseModel,
                entity.getStatusAnalise(),
                entity.getCodigoImagem()
        );
    }
}
