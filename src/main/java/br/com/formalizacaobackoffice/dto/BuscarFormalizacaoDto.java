package br.com.formalizacaobackoffice.dto;

import br.com.formalizacaobackoffice.model.ObjetoAnaliseFormalizacao;
import br.com.formalizacaobackoffice.model.Pessoa;
import br.com.formalizacaobackoffice.model.TipoFormalizacao;

import java.util.List;

public class BuscarFormalizacaoDto {
    private String codigoFormalizacao;
    private TipoFormalizacao tipoFormalizacao;
    private Pessoa pessoa;
    private List<ObjetoAnaliseFormalizacao> objetoAnaliseFormalizacaoLista;
}
