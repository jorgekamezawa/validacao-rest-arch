package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ObjetoAnalise {
    private long codigoObjetoAnalise;
    private String nomeObjetoAnalise;
    private List<MotivoDevolucao> motivoDevolucaoLista = new ArrayList<>();
}
