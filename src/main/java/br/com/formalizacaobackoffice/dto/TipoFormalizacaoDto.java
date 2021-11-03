package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TipoFormalizacaoDto {
    @JsonProperty("nome_processo")
    private String nomeProcesso;
    @JsonProperty("nome_origem")
    private String nomeOrigem;
    @JsonProperty("nome_segmentacao")
    private String nomeSegmentacao;
}
