package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MotivoDevolucaoFormalizacao {
    private long codigoMotivoDevolucaoFormalizacao;
    private MotivoDevolucao motivoDevolucao;
    private String status;
    private ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao;
}
