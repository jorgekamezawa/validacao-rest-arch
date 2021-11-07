package br.com.formalizacaobackoffice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "formalizadores")
public class FormalizadorEntity {
    @Id
    @Column(name = "codigo_formalizador", nullable = false, unique = true)
    private String codigoFormalizador;
    @Column(name = "nome_formalizador", nullable = false, unique = true)
    private String nomeFormalizador;
    @Column(name = "url_envio_formalizador", nullable = false)
    private String urlEnvioFormalizador;
}
