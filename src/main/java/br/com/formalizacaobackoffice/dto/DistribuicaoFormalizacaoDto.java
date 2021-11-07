package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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
