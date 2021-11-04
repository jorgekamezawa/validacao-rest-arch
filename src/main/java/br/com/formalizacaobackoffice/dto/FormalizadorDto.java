package br.com.formalizacaobackoffice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormalizadorDto {

    @JsonProperty("codigo_formalizador")
    private String codigoFormalizador;
    @JsonProperty("nome_formalizador")
    private String nomeFormalizador;
    @JsonProperty("url_envio_formalizador")
    private String urlEnvioFormalizador;
}
