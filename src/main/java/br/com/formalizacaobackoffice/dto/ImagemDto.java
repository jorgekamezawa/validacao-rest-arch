package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImagemDto {
    @JsonProperty("nome_objeto_analise")
    private String nomeObjetoAnalise;
    @JsonProperty("codigo_imagem")
    private String codigoImagem;
}
