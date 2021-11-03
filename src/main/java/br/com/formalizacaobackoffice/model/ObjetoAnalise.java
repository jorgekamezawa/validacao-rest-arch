package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ObjetoAnalise {
    private long codigoObjetoAnalise;
    private String nomeObjetoAnalise;
    private List<MotivoDevolucao> motivoDevolucaoLista;
}
