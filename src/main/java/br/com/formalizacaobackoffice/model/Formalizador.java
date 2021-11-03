package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Formalizador {
    private String codigoFormalizador;
    private String nomeFormalizador;
    private String urlEnvioFormalizador;
}
