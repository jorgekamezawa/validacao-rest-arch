package br.com.formalizacaobackoffice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "formalizacoes")
public class FormalizacaoEntity {
    @Id
    @Column(name = "codigo_formalizacao", nullable = false, unique = true)
    private String codigoFormalizacao;
    @ManyToOne
    @JoinColumn(name = "tipo_formalizacao_codigo")
    private TipoFormalizacaoEntity tipoFormalizacaoEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_codigo")
    private PessoaEntity pessoaEntity;
    @ManyToOne
    @JoinColumn(name = "distribuicao_formalizacao_codigo")
    private DistribuicaoFormalizacaoEntity distribuicaoFormalizacaoEntity;
    @OneToMany(mappedBy = "formalizacaoEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ObjetoAnaliseFormalizacaoEntity> objetoAnaliseFormalizacaoEntityLista = new ArrayList<>();
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "dataHora_formalizacao_ultima_atualizacao_status", nullable = false)
    private LocalDateTime dataHoraFormalizacaoUltimaAtualizacaoDeStatus;

    public void adicionarObjetoAnaliseFormalizacao(List<ObjetoAnaliseFormalizacaoEntity> objetoAnaliseFormalizacaoEntityLista) {
        objetoAnaliseFormalizacaoEntityLista.forEach(objetoAnaliseFormalizacaoEntity -> {
            if (!this.objetoAnaliseFormalizacaoEntityLista.contains(objetoAnaliseFormalizacaoEntity)) {
                this.objetoAnaliseFormalizacaoEntityLista.add(objetoAnaliseFormalizacaoEntity);
            }
            objetoAnaliseFormalizacaoEntity.adicionarFormalizacao(this);
        });
    }
}
