package br.com.formalizacaobackoffice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipos_formalizacao")
public class TipoFormalizacaoEntity {

    @Id
    @Column(name = "codigo_tipo_formalizacao", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoTipoFormalizacao;
    @ManyToOne
    @JoinColumn(name = "processo_codigo")
    private ProcessoEntity processoEntity;
    @ManyToOne
    @JoinColumn(name = "origem_codigo")
    private OrigemEntity origemEntity;
    @ManyToOne
    @JoinColumn(name = "segmentacao_codigo")
    private SegmentacaoEntity segmentacaoEntity;
    @ManyToMany
    @JoinTable(name = "tipos_formalizacao_objetos_analise",
            joinColumns = @JoinColumn(name = "codigo_tipo_formalizacao"),
            inverseJoinColumns = @JoinColumn(name = "codigo_objeto_analise"))
    private List<ObjetoAnaliseEntity> objetoAnaliseEntityLista = new ArrayList<>();

    public TipoFormalizacaoEntity(long codigoTipoFormalizacao, ProcessoEntity processoEntity, OrigemEntity origemEntity, SegmentacaoEntity segmentacaoEntity) {
        this.codigoTipoFormalizacao = codigoTipoFormalizacao;
        this.processoEntity = processoEntity;
        this.origemEntity = origemEntity;
        this.segmentacaoEntity = segmentacaoEntity;
    }
}
