package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MotivoDevolucao {
    private long codigoMotivoDevolucao;
    private String nomeMotivoDevolucao;
}
