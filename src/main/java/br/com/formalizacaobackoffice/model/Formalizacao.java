package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Formalizacao {
    private String codigoFormalizacao;
    private TipoFormalizacao tipoFormalizacao;
    private Pessoa pessoa;
    private DistribuicaoFormalizacao distribuicaoFormalizacao;
    private List<ObjetoAnaliseFormalizacao> objetoAnaliseFormalizacaoLista = new ArrayList<>();
    private String status;
    private LocalDateTime dataHoraFormalizacaoUltimaAtualizacaoDeStatus;

    public void criarFormalizacao(TipoFormalizacao tipoFormalizacao, Pessoa pessoa) {
        this.codigoFormalizacao = UUID.randomUUID().toString();
        this.tipoFormalizacao = tipoFormalizacao;
        this.pessoa = pessoa;
        this.status = "CRIADO";
        this.dataHoraFormalizacaoUltimaAtualizacaoDeStatus = LocalDateTime.now();
    }

    public void adicionarObjetoAnaliseFormalizacao(ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao) {
        if (!this.objetoAnaliseFormalizacaoLista.contains(objetoAnaliseFormalizacao)) {
            this.objetoAnaliseFormalizacaoLista.add(objetoAnaliseFormalizacao);
            objetoAnaliseFormalizacao.atribuirFormalizacao(this);
        }
    }

    public void adicionarDistribuicao(DistribuicaoFormalizacao distribuicaoFormalizacao) {
        this.distribuicaoFormalizacao = distribuicaoFormalizacao;
    }

    public void alterarStatus(String status) {
        this.status = status;
        this.dataHoraFormalizacaoUltimaAtualizacaoDeStatus = LocalDateTime.now();
    }
}
