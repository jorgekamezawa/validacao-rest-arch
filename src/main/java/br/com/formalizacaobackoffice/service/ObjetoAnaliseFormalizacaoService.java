package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.dto.ImagemDto;
import br.com.formalizacaobackoffice.exception.ObjetoAnaliseFormalizacaoNotAcceptableException;
import br.com.formalizacaobackoffice.model.Formalizacao;
import br.com.formalizacaobackoffice.model.ObjetoAnaliseFormalizacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetoAnaliseFormalizacaoService {
    public void criarListaObjetoAnaliseFormalizacao(Formalizacao formalizacao, List<ImagemDto> imagemLista) {
        formalizacao.getTipoFormalizacao().getObjetoAnaliseLista().forEach(objetoAnalise -> {
            ImagemDto imagem = imagemLista.stream().filter(imagemDto -> objetoAnalise.getNomeObjetoAnalise().equalsIgnoreCase(imagemDto.getNomeObjetoAnalise()))
                    .findFirst().orElseThrow(() -> new ObjetoAnaliseFormalizacaoNotAcceptableException("Nao foi possivel encontrar o objeto de analise " +
                            objetoAnalise.getNomeObjetoAnalise() + " nos objetos de analise enviados!"));

            formalizacao.adicionarObjetoAnaliseFormalizacao(
                    new ObjetoAnaliseFormalizacao(
                            formalizacao.getCodigoFormalizacao(),
                            objetoAnalise,
                            imagem.getCodigoImagem()));
        });
    }
}
