package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MotivoDevolucaoFormalizacaoDto {

    @JsonProperty("nome_motivo_devolucao")
    private String nomeMotivoDevolucaoDto;
    @JsonProperty("status_motivo_devolucao")
    private String statusMotivoDevolucaoDto;
}
