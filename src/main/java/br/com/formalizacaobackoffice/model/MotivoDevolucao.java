package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MotivoDevolucao {
    private long codigoMotivoDevolucao;
    private String nomeMotivoDevolucao;
}
