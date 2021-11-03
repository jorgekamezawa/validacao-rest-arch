package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.dto.request.RequisicaoFormalizacaoDto;
import br.com.formalizacaobackoffice.exception.FormalizadorRestClientException;
import br.com.formalizacaobackoffice.model.Formalizacao;
import br.com.formalizacaobackoffice.restclient.FormalizadorRestoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormalizadorService {
    @Autowired
    private FormalizadorRestoClient formalizadorRestoClient;

    public void enviarFormalizacaoParaFormalizador(Formalizacao formalizacao) {
        RequisicaoFormalizacaoDto requisicaoFormalizacaoDto = new RequisicaoFormalizacaoDto(formalizacao.getCodigoFormalizacao());
        try {
            formalizadorRestoClient.enviarFormalizador(requisicaoFormalizacaoDto);
        } catch (Exception e) {
            throw new FormalizadorRestClientException("Erro ao chamar a API de Formalizador");
        }
    }
}
