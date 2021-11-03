package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.DistribuicaoFormalizacao;
import br.com.formalizacaobackoffice.model.Formalizador;
import br.com.formalizacaobackoffice.model.TipoFormalizacao;
import br.com.formalizacaobackoffice.persistence.entity.DistribuicaoFormalizacaoEntity;
import br.com.formalizacaobackoffice.persistence.entity.FormalizadorEntity;
import br.com.formalizacaobackoffice.persistence.entity.TipoFormalizacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DistribuicaoFormalizacaoMapper {
    @Autowired
    private FormalizadorMapper formalizadorMapper;
    @Autowired
    private TipoFormalizacaoMapper tipoFormalizacaoMapper;

    public List<DistribuicaoFormalizacao> converterParaModel(List<DistribuicaoFormalizacaoEntity> entityLista) {
        List<DistribuicaoFormalizacao> modellista = new ArrayList<>();
        entityLista.forEach(entity -> {
            modellista.add(converterParaModel(entity));
        });
        return modellista;
    }

    public DistribuicaoFormalizacao converterParaModel(DistribuicaoFormalizacaoEntity entity) {
        TipoFormalizacao tipoFormalizacaoModel = tipoFormalizacaoMapper.converterParaModel(entity.getTipoFormalizacaoEntity());
        Formalizador formalizadorModel = formalizadorMapper.converterParaModel(entity.getFormalizadorEntity());
        return new DistribuicaoFormalizacao(
                entity.getCodigoDistribuicaoFormalizacao(),
                tipoFormalizacaoModel,
                formalizadorModel,
                entity.getPorcentagemDeDistribuicao(),
                entity.getContadorDeDistribuicao());
    }

    public DistribuicaoFormalizacaoEntity converterParaEntity(DistribuicaoFormalizacao model) {
        TipoFormalizacaoEntity tipoFormalizacaoEntity = tipoFormalizacaoMapper.converterParaEntity(model.getTipoFormalizacao());
        FormalizadorEntity formalizadorEntity = formalizadorMapper.converterParaEntity(model.getFormalizador());
        return new DistribuicaoFormalizacaoEntity(
                model.getCodigoDistribuicaoFormalizacao(),
                tipoFormalizacaoEntity,
                formalizadorEntity,
                model.getPorcentagemDeDistribuicao(),
                model.getContadorDeDistribuicao()
        );
    }
}
