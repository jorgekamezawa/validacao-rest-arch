package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.*;
import br.com.formalizacaobackoffice.persistence.entity.*;
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
        DistribuicaoFormalizacaoEntity distribuicaoFormalizacaoEntity = model.getDistribuicaoFormalizacao() == null ? null : distribuicaoFormalizacaoMapper.converterParaEntity(model.getDistribuicaoFormalizacao());
        List<ObjetoAnaliseFormalizacaoEntity> objetoAnaliseFormalizacaoEntityLista = model.getObjetoAnaliseFormalizacaoLista() == null ? null : objetoAnaliseFormalizacaoMapper.converterParaEntity(model.getObjetoAnaliseFormalizacaoLista());
        return new FormalizacaoEntity(
                model.getCodigoFormalizacao(),
                tipoFormalizacaoEntity,
                pessoaEntity,
                distribuicaoFormalizacaoEntity,
                objetoAnaliseFormalizacaoEntityLista,
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
