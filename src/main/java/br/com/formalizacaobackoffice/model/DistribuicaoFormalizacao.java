package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DistribuicaoFormalizacao {
    private long codigoDistribuicaoFormalizacao;
    private TipoFormalizacao tipoFormalizacao;
    private Formalizador formalizador;
    private double porcentagemDeDistribuicao;
    private long contadorDeDistribuicao;

    public void incrementarNoContadorDeDistribuicao() {
        this.contadorDeDistribuicao++;
    }

    public void zerarContadorDeDistribuicao() {
        this.contadorDeDistribuicao = 0;
    }

    public void atualizarPorcentagem(double porcentagemDeDistribuicao) {
        this.porcentagemDeDistribuicao = porcentagemDeDistribuicao;
    }
}
