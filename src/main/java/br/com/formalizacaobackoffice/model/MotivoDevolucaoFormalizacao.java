package br.com.formalizacaobackoffice.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MotivoDevolucaoFormalizacao {
    private long codigoMotivoDevolucaoFormalizacao;
    private MotivoDevolucao motivoDevolucao;
    private String status;
    //    private long contadorDeQuantidadeDeDevolucao;
    private ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao;

    public MotivoDevolucaoFormalizacao(long codigoMotivoDevolucaoFormalizacao, MotivoDevolucao motivoDevolucao, String status) {
        this.codigoMotivoDevolucaoFormalizacao = codigoMotivoDevolucaoFormalizacao;
        this.motivoDevolucao = motivoDevolucao;
        this.status = status;
    }

    public MotivoDevolucaoFormalizacao(MotivoDevolucao motivoDevolucao, String status) {
        this.motivoDevolucao = motivoDevolucao;
        this.status = status;
    }

    public void alterarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    public void atribuirObjetoAnaliseFormalizacao(ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao) {
        this.objetoAnaliseFormalizacao = objetoAnaliseFormalizacao;
    }
}
