package br.com.formalizacaobackoffice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "distribuicoes_formalizacao")
public class DistribuicaoFormalizacaoEntity {

    @Id
    @Column(name = "codigo_distribuicao_formalizacao", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoDistribuicaoFormalizacao;
    @ManyToOne
    @JoinColumn(name = "tipo_formalizacao_codigo")
    private TipoFormalizacaoEntity tipoFormalizacaoEntity;
    @ManyToOne
    @JoinColumn(name = "formalizador_codigo")
    private FormalizadorEntity formalizadorEntity;
    @Column(name = "porcentagem_distribuicao", nullable = false)
    private double porcentagemDeDistribuicao;
    @Column(name = "contador_distribuicao", nullable = false)
    private long contadorDeDistribuicao;
}
