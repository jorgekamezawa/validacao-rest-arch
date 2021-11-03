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
@Table(name = "motivos_devolucao")
public class MotivoDevolucaoEntity {
    @Id
    @Column(name = "codigo_motivo_devolucao", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoMotivoDevolucao;
    @Column(name = "nome_motivo_devolucao", nullable = false, unique = true)
    private String nomeMotivoDevolucao;
    @ManyToMany(mappedBy = "motivoDevolucaoEntityLista")
    private List<ObjetoAnaliseEntity> objetoAnaliseEntityLista = new ArrayList<>();

    public MotivoDevolucaoEntity(long codigoMotivoDevolucao, String nomeMotivoDevolucao) {
        this.codigoMotivoDevolucao = codigoMotivoDevolucao;
        this.nomeMotivoDevolucao = nomeMotivoDevolucao;
    }
}
