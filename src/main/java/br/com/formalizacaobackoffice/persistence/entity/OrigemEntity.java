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
@Table(name = "origens")
public class OrigemEntity {
    @Id
    @Column(name = "codigo_origem", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoOrigem;
    @Column(name = "nome_origem", nullable = false, unique = true)
    private String nomeOrigem;
}
