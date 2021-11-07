package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EfetivarFormalizacaoDto {
    @JsonProperty("codigo_formalizacao")
    private String codigoFormalizacaoDto;
    @JsonProperty("objeto_analise_formalizacao")
    private List<ObjetoAnaliseFormalizacaoDto> objetoAnaliseFormalizacaoDtoLista = new ArrayList<>();
}
