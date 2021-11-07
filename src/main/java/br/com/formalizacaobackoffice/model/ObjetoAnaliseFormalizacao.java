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
public class ObjetoAnaliseFormalizacao {
    private long codigoObjetoAnaliseFormalizacao;
    private String codigoFormalizacao;
    private ObjetoAnalise objetoAnalise;
    private String statusAnalise;
    private List<MotivoDevolucaoFormalizacao> motivoDevolucaoFormalizacaoLista = new ArrayList<>();
    private String codigoImagem;

    public void atribuirFormalizacao(Formalizacao formalizacao) {
        this.codigoFormalizacao = formalizacao.getCodigoFormalizacao();
    }

    public ObjetoAnaliseFormalizacao(String codigoFormalizacao, ObjetoAnalise objetoAnalise, String codigoImagem) {
        this.codigoFormalizacao = codigoFormalizacao;
        this.objetoAnalise = objetoAnalise;
        this.statusAnalise = "CRIADO";
        this.codigoImagem = codigoImagem;
    }

    public void alterarStatus(String statusAnalise) {
        this.statusAnalise = statusAnalise;
    }

    public void adicionarMotivoDevolucaoFormalizacao(MotivoDevolucaoFormalizacao motivoDevolucaoFormalizacao) {
        motivoDevolucaoFormalizacao.atribuirObjetoAnaliseFormalizacao(this);
        this.motivoDevolucaoFormalizacaoLista.add(motivoDevolucaoFormalizacao);
    }
}
