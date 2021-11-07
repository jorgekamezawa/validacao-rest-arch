package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TipoFormalizacaoDto {
    @JsonProperty("nome_processo")
    private String nomeProcesso;
    @JsonProperty("nome_origem")
    private String nomeOrigem;
    @JsonProperty("nome_segmentacao")
    private String nomeSegmentacao;
}
