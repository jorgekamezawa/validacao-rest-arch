package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DistribuirFormalizacaoDto {
    @JsonProperty("tipo_formalizacao")
    private TipoFormalizacaoDto tipoFormalizacaoDto;
    @JsonProperty("pessoa")
    private PessoaDto pessoaDto;
    @JsonProperty("imagens")
    private List<ImagemDto> imagemLista = new ArrayList<>();
}
