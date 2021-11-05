package br.com.formalizacaobackoffice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "motivos_devolucao_formalizacao")
public class MotivoDevolucaoFormalizacaoEntity {
    @Id
    @Column(name = "codigo_motivo_devolucao_formalizacao", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoMotivoDevolucaoFormalizacao;
    @ManyToOne
    @JoinColumn(name = "motivo_devolucao_codigo")
    private MotivoDevolucaoEntity motivoDevolucaoEntity;
    @Column(name = "status", nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "objeto_analise_formalizacao_codigo")
    private ObjetoAnaliseFormalizacaoEntity objetoAnaliseFormalizacaoEntity;

    public MotivoDevolucaoFormalizacaoEntity(long codigoMotivoDevolucaoFormalizacao, MotivoDevolucaoEntity motivoDevolucaoEntity, String status) {
        this.codigoMotivoDevolucaoFormalizacao = codigoMotivoDevolucaoFormalizacao;
        this.motivoDevolucaoEntity = motivoDevolucaoEntity;
        this.status = status;
    }

    public void adicionarObjetoAnaliseFormalizacao(ObjetoAnaliseFormalizacaoEntity objetoAnaliseFormalizacaoEntity) {
        this.objetoAnaliseFormalizacaoEntity = objetoAnaliseFormalizacaoEntity;
    }
}
