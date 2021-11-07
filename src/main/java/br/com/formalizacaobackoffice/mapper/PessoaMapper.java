package br.com.formalizacaobackoffice.mapper;

import br.com.formalizacaobackoffice.model.Pessoa;
import br.com.formalizacaobackoffice.persistence.entity.PessoaEntity;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {
    public PessoaEntity converterParaEntity(Pessoa model) {
        return new PessoaEntity(
                model.getCodigoPessoa(),
                model.getCodigoPessoaTemporario()
        );
    }

    public Pessoa converterParaModel(PessoaEntity entity) {
        return new Pessoa(
                entity.getCodigoPessoa(),
                entity.getCodigoPessoaTemporario()
        );
    }
}
