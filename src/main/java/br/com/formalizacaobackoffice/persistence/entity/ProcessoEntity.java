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
@Table(name = "processos")
public class ProcessoEntity {
    @Id
    @Column(name = "codigo_processo", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigoProcesso;
    @Column(name = "nome_processo", nullable = false, unique = true)
    private String nomeProcesso;
}
