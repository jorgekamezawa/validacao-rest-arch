package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EfetivarFormalizacaoDto {

    @JsonProperty("codigo_formalizacao")
    private String codigoFormalizacaoDto;
    @JsonProperty("objeto_analise_formalizacao")
    private List<ObjetoAnaliseFormalizacaoDto> objetoAnaliseFormalizacaoDtoLista;
}
