package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//TODO:Alterar nome para detalhe motivo devolucao
//TODO: Extender de formalizacao ao inves de deixar no nome
public class MotivoDevolucaoFormalizacao {
    private long codigoMotivoDevolucaoFormalizacao;
    private MotivoDevolucao motivoDevolucao;
    private String status;
    //TODO: Adicionar um contador de vezes que foi devolvido, e data e hora
    @Setter
    private ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao;

    public MotivoDevolucaoFormalizacao(MotivoDevolucao motivoDevolucao, String status) {
        this.motivoDevolucao = motivoDevolucao;
        this.status = status;
    }

    public void alterarStatus(String novoStatus) {
        this.status = novoStatus;
    }
}
