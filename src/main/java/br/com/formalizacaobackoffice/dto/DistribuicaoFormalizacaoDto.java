package br.com.formalizacaobackoffice.dto;

import br.com.formalizacaobackoffice.dto.FormalizadorDto;
import br.com.formalizacaobackoffice.dto.TipoFormalizacaoDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistribuicaoFormalizacaoDto {

    @JsonProperty("codigo_distribuicao_formalizacao")
    private long codigoDistribuicaoFormalizacao;
    @JsonProperty("tipo_formalizacao")
    private TipoFormalizacaoDto tipoFormalizacaoDto;
    @JsonProperty("formalizador")
    private FormalizadorDto formalizadorDto;
    @JsonProperty("porcentagem_distribuicao")
    private double porcentagemDeDistribuicao;
}
