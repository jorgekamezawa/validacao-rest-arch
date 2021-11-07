package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Formalizador {
    private String codigoFormalizador;
    private String nomeFormalizador;
    private String urlEnvioFormalizador;
}
