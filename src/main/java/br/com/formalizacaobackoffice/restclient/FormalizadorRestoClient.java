package br.com.formalizacaobackoffice.restclient;

import br.com.formalizacaobackoffice.dto.RequisicaoFormalizacaoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "formalizador", url = "http://localhost:8081/formalizador/formalizacao")
public interface FormalizadorRestoClient {
    @RequestMapping(method = RequestMethod.POST, value = "", consumes = "application/json")
    ResponseEntity<?> enviarFormalizador(@RequestBody RequisicaoFormalizacaoDto requisicaoFormalizacaoDto);
}
