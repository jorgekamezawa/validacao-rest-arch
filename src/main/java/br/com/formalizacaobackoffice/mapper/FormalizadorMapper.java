package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.Formalizador;
import br.com.formalizacaobackoffice.persistence.entity.FormalizadorEntity;
import org.springframework.stereotype.Component;

@Component
public class FormalizadorMapper {

    public Formalizador converterParaModel(FormalizadorEntity entity) {
        return new Formalizador(
                entity.getCodigoFormalizador(),
                entity.getNomeFormalizador(),
                entity.getUrlEnvioFormalizador()
        );
    }

    public FormalizadorEntity converterParaEntity(Formalizador model) {
        return new FormalizadorEntity(
                model.getCodigoFormalizador(),
                model.getNomeFormalizador(),
                model.getUrlEnvioFormalizador()
        );
    }
}
