package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipoFormalizacao {
    private long codigoTipoFormalizacao;
    private Processo processo;
    private Origem origem;
    private Segmentacao segmentacao;
    private List<ObjetoAnalise> objetoAnaliseLista;

    public TipoFormalizacao(Processo processo, Origem origem, Segmentacao segmentacao) {
        this.processo = processo;
        this.origem = origem;
        this.segmentacao = segmentacao;
    }
}
