package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ObjetoAnaliseFormalizacaoDto {
    @JsonProperty("codigo_objeto_analise_formalizacao")
    private long codigoObjetoAnaliseFormalizacaoDto;
    @JsonProperty("nome_objeto_analise")
    private String nomeObjetoAnaliseDto;
    @JsonProperty("status_objeto_analise")
    private String statusObjetoAnaliseDto;
    @JsonProperty("motivo_devolucao")
    @JsonIgnoreProperties
    private List<MotivoDevolucaoFormalizacaoDto> motivoDevolucaoFormalizacaoDtoLista = new ArrayList<>();
}
