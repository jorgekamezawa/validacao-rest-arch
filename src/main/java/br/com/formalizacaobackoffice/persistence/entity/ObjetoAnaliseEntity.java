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
@Table(name = "objetos_analise")
public class ObjetoAnaliseEntity {
    @Id
    @Column(name = "codigo_objeto_analise", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoObjetoAnalise;
    @Column(name = "nome_objeto_analise", nullable = false, unique = true)
    private String nomeObjetoAnalise;
    @ManyToMany
    @JoinTable(name = "objetos_analise_motivos_devolucao",
            joinColumns = @JoinColumn(name = "objeto_analise_codigo"),
            inverseJoinColumns = @JoinColumn(name = "motivo_devolucao_codigo"))
    private List<MotivoDevolucaoEntity> motivoDevolucaoEntityLista = new ArrayList<>();
    @ManyToMany(mappedBy = "objetoAnaliseEntityLista")
    private List<TipoFormalizacaoEntity> tipoFormalizacaoEntityLista = new ArrayList<>();

    public ObjetoAnaliseEntity(long codigoObjetoAnalise, String nomeObjetoAnalise, List<MotivoDevolucaoEntity> motivoDevolucaoEntityLista) {
        this.codigoObjetoAnalise = codigoObjetoAnalise;
        this.nomeObjetoAnalise = nomeObjetoAnalise;
        this.motivoDevolucaoEntityLista = motivoDevolucaoEntityLista;
    }
}
