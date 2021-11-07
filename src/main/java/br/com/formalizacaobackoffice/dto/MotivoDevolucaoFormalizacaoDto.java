package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MotivoDevolucaoFormalizacaoDto {
    @JsonProperty("nome_motivo_devolucao")
    private String nomeMotivoDevolucaoDto;
    @JsonProperty("status_motivo_devolucao")
    private String statusMotivoDevolucaoDto;
}
