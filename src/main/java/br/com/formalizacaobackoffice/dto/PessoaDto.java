package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PessoaDto {
    @JsonProperty("codigo_pessoa")
    private String codigoPessoa;
    @JsonProperty("codigo_pessoa_temporario")
    private String codigoPessoaemporario;
}
