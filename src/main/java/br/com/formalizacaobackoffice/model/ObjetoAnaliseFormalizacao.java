package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ObjetoAnaliseFormalizacao {
    private long codigoObjetoAnaliseFormalizacao;
    private String codigoFormalizacao;
    private ObjetoAnalise objetoAnalise;
    private String statusAnalise;
    private List<MotivoDevolucaoFormalizacao> motivoDevolucaoFormalizacaoLista;
    private String codigoImagem;

    public ObjetoAnaliseFormalizacao(String codigoFormalizacao, ObjetoAnalise objetoAnalise, String codigoImagem) {
        this.codigoFormalizacao = codigoFormalizacao;
        this.objetoAnalise = objetoAnalise;
        this.statusAnalise = "CRIADO";
        this.codigoImagem = codigoImagem;
    }

    public ObjetoAnaliseFormalizacao(long codigoObjetoAnaliseFormalizacao, String codigoFormalizacao, ObjetoAnalise objetoAnalise, String statusAnalise, String codigoImagem) {
        this.codigoObjetoAnaliseFormalizacao = codigoObjetoAnaliseFormalizacao;
        this.codigoFormalizacao = codigoFormalizacao;
        this.objetoAnalise = objetoAnalise;
        this.statusAnalise = statusAnalise;
        this.codigoImagem = codigoImagem;
    }
}
