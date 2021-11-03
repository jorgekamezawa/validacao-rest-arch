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
@Table(name = "segmentacoes")
public class SegmentacaoEntity {
    @Id
    @Column(name = "codigo_segmentacao", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoSegmentacao;
    @Column(name = "nome_segmentacao", nullable = false, unique = true)
    private String nomeSegmentacao;
}
