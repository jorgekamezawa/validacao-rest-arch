package br.com.formalizacaobackoffice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "origens")
public class OrigemEntity {
    @Id
    @Column(name = "codigo_origem", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoOrigem;
    @Column(name = "nome_origem", nullable = false, unique = true)
    private String nomeOrigem;
}
