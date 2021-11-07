package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.*;
import br.com.formalizacaobackoffice.persistence.entity.DistribuicaoFormalizacaoEntity;
import br.com.formalizacaobackoffice.persistence.entity.FormalizacaoEntity;
import br.com.formalizacaobackoffice.persistence.entity.PessoaEntity;
import br.com.formalizacaobackoffice.persistence.entity.TipoFormalizacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FormalizacaoMapper {
    @Autowired
    private TipoFormalizacaoMapper tipoFormalizacaoMapper;
    @Autowired
    private PessoaMapper pessoaMapper;
    @Autowired
    private DistribuicaoFormalizacaoMapper distribuicaoFormalizacaoMapper;
    @Autowired
    private ObjetoAnaliseFormalizacaoMapper objetoAnaliseFormalizacaoMapper;

    public FormalizacaoEntity converterParaEntity(Formalizacao model) {
        TipoFormalizacaoEntity tipoFormalizacaoEntity = tipoFormalizacaoMapper.converterParaEntity(model.getTipoFormalizacao());
        PessoaEntity pessoaEntity = pessoaMapper.converterParaEntity(model.getPessoa());
        DistribuicaoFormalizacaoEntity distribuicaoFormalizacaoEntity = distribuicaoFormalizacaoMapper.converterParaEntity(model.getDistribuicaoFormalizacao());
        return new FormalizacaoEntity(
                model.getCodigoFormalizacao(),
                tipoFormalizacaoEntity,
                pessoaEntity,
                distribuicaoFormalizacaoEntity,
                model.getStatus(),
                model.getDataHoraFormalizacaoUltimaAtualizacaoDeStatus()
        );
    }

    public List<Formalizacao> converterParaModel(List<FormalizacaoEntity> entityLista) {
        List<Formalizacao> modelLista = new ArrayList<>();
        entityLista.forEach(entity -> {
            modelLista.add(converterParaModel(entity));
        });
        return modelLista;
    }

    public Formalizacao converterParaModel(FormalizacaoEntity entity) {
        TipoFormalizacao tipoFormalizacaoModel = tipoFormalizacaoMapper.converterParaModel(entity.getTipoFormalizacaoEntity());
        Pessoa pessoaModel = pessoaMapper.converterParaModel(entity.getPessoaEntity());
        DistribuicaoFormalizacao distribuicaoFormalizacaoModel = entity.getDistribuicaoFormalizacaoEntity() == null ? null : distribuicaoFormalizacaoMapper.converterParaModel(entity.getDistribuicaoFormalizacaoEntity());
        List<ObjetoAnaliseFormalizacao> objetoAnaliseFormalizacaoLista = entity.getObjetoAnaliseFormalizacaoEntityLista() == null ? null : objetoAnaliseFormalizacaoMapper.converterParaModel(entity.getObjetoAnaliseFormalizacaoEntityLista());
        return new Formalizacao(
                entity.getCodigoFormalizacao(),
                tipoFormalizacaoModel,
                pessoaModel,
                distribuicaoFormalizacaoModel,
                objetoAnaliseFormalizacaoLista,
                entity.getStatus(),
                entity.getDataHoraFormalizacaoUltimaAtualizacaoDeStatus()
        );
    }
}
