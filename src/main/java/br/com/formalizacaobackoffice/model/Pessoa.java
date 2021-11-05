package br.com.formalizacaobackoffice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
//    private long idPessoa;
    private String codigoPessoa;
    private String codigoPessoaTemporario;
    private Formalizacao formalizacao;

    public Pessoa(String codigoPessoa, String codigoPessoaTemporario) {
        this.codigoPessoa = codigoPessoa;
        this.codigoPessoaTemporario = codigoPessoaTemporario;
    }

//    public Pessoa(long idPessoa, String codigoPessoa, String codigoPessoaemporario) {
//        this.idPessoa = idPessoa;
//        this.codigoPessoa = codigoPessoa;
//        this.codigoPessoaemporario = codigoPessoaemporario;
//    }
}
