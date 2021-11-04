package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoFormalizacaoDto {
    @JsonProperty("codigo_formalizacao")
    private String codigoFormalizacao;
}
