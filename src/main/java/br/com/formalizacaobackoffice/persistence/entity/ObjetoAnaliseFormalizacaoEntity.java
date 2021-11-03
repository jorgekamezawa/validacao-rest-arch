package br.com.formalizacaobackoffice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "objetos_analise_formalizacao")
public class ObjetoAnaliseFormalizacaoEntity {

    @Id
    @Column(name = "codigo_objeto_analise_formalizacao", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoObjetoAnaliseFormalizacao;
    @ManyToOne
    @JoinColumn(name = "formalizacao_codigo")
    private FormalizacaoEntity formalizacaoEntity;
    @ManyToOne
    @JoinColumn(name = "objeto_analise_codigo")
    private ObjetoAnaliseEntity objetoAnaliseEntity;
    @Column(name = "status_analise", nullable = false)
    private String statusAnalise;
    @OneToMany(mappedBy = "objetoAnaliseFormalizacaoEntity", fetch = FetchType.LAZY)
    private List<MotivoDevolucaoFormalizacaoEntity> motivoDevolucaoFormalizacaoEntityLista = new ArrayList<>();
    @Column(name = "codigo_imagem", nullable = false)
    private String codigoImagem;

    public ObjetoAnaliseFormalizacaoEntity(ObjetoAnaliseEntity objetoAnaliseEntity, String statusAnalise, String codigoImagem) {
        this.objetoAnaliseEntity = objetoAnaliseEntity;
        this.statusAnalise = statusAnalise;
        this.codigoImagem = codigoImagem;
    }

    public void adicionarFormalizacao(FormalizacaoEntity formalizacaoEntity) {
        this.formalizacaoEntity = formalizacaoEntity;
    }
}
