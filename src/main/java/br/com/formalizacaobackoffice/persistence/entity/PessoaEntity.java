package br.com.formalizacaobackoffice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoas")
public class PessoaEntity {
    @Id
    @Column(name = "codigo_pessoa", nullable = false, unique = true)
    private String codigoPessoa;
    @Column(name = "codigo_pessoa_temporario", nullable = false)
    private String codigoPessoaTemporario;
    @OneToOne(mappedBy = "pessoaEntity")
    private FormalizacaoEntity formalizacaoEntity;

    public PessoaEntity(String codigoPessoa, String codigoPessoaTemporario) {
        this.codigoPessoa = codigoPessoa;
        this.codigoPessoaTemporario = codigoPessoaTemporario;
    }
}
