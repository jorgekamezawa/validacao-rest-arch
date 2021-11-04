package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.dto.FormalizadorDto;
import br.com.formalizacaobackoffice.dto.TipoFormalizacaoDto;
import br.com.formalizacaobackoffice.dto.DistribuicaoFormalizacaoDto;
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

    public List<DistribuicaoFormalizacaoEntity> converterParaEntity(List<DistribuicaoFormalizacao> modelLista) {
        List<DistribuicaoFormalizacaoEntity> entitylista = new ArrayList<>();
        modelLista.forEach(model -> {
            entitylista.add(converterParaEntity(model));
        });
        return entitylista;
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

    public List<DistribuicaoFormalizacaoDto> converterParaDto(List<DistribuicaoFormalizacao> modelLista) {
        List<DistribuicaoFormalizacaoDto> dtoLista = new ArrayList<>();
        modelLista.forEach(model -> {
            dtoLista.add(converterParaDto(model));
        });
        return dtoLista;
    }

    private DistribuicaoFormalizacaoDto converterParaDto(DistribuicaoFormalizacao model) {
        TipoFormalizacaoDto tipoFormalizacaoDto = tipoFormalizacaoMapper.converterParaDto(model.getTipoFormalizacao());
        FormalizadorDto formalizadorDto = formalizadorMapper.converterParaDto(model.getFormalizador());
        return new DistribuicaoFormalizacaoDto(
                model.getCodigoDistribuicaoFormalizacao(),
                tipoFormalizacaoDto,
                formalizadorDto,
                model.getPorcentagemDeDistribuicao()
        );
    }
}
