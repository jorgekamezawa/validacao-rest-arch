package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.exception.DistribuicaoException;
import br.com.formalizacaobackoffice.model.DistribuicaoFormalizacao;
import br.com.formalizacaobackoffice.model.Formalizacao;
import br.com.formalizacaobackoffice.persistence.adapter.DistribuicaoFormalizacaoPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DistribuicaoFormalizacaoService {
    @Autowired
    private DistribuicaoFormalizacaoPersistenceAdapter distribuicaoFormalizacaoPersistenceAdapter;
    @Autowired
    private FormalizacaoService formalizacaoService;
    @Autowired
    private FormalizadorService formalizadorService;
    @Autowired
    private PessoaService pessoaService;

    public void distribuirFormalizacao(Formalizacao formalizacao) {
        List<DistribuicaoFormalizacao> distribuicaoFormalizacaoListaPorTipoFormalizacao =
                distribuicaoFormalizacaoPersistenceAdapter.buscarDistribuicaoFormalizacaoPorTipoFormalizacaoEPorcentagemMaiorQueZero(formalizacao.getTipoFormalizacao());

        long somaDeTodosContadores = distribuicaoFormalizacaoListaPorTipoFormalizacao.stream().mapToLong(DistribuicaoFormalizacao::getContadorDeDistribuicao).sum();
        DistribuicaoFormalizacao distribuicaoSelecionada;
        if (somaDeTodosContadores == 0) {
            distribuicaoSelecionada = distribuicaoFormalizacaoListaPorTipoFormalizacao.stream().max(Comparator.comparing(DistribuicaoFormalizacao::getPorcentagemDeDistribuicao))
                    .orElseThrow(() -> new DistribuicaoException("Erro ao buscar a DistribuicaoFormalizacao quando soma de contadores  é igual a 0!"));
        } else {
            distribuicaoSelecionada = distribuicaoFormalizacaoListaPorTipoFormalizacao.stream()
                    .sorted(Comparator.comparing(DistribuicaoFormalizacao::getPorcentagemDeDistribuicao).reversed())
                    .filter(d -> {
                        double calculoPorcentagem = (d.getContadorDeDistribuicao() * 100.00) / somaDeTodosContadores;
                        return calculoPorcentagem <= d.getPorcentagemDeDistribuicao();
                    }).findFirst().orElseThrow(() -> new DistribuicaoException("Erro ao executar a regra de distribuicao pra formalizacao: " + formalizacao.getCodigoFormalizacao()));
        }
        formalizacao.adicionarDistribuicao(distribuicaoSelecionada);
        formalizadorService.enviarFormalizacaoParaFormalizador(formalizacao);
        distribuicaoSelecionada.incrementarNoContadorDeDistribuicao();
        salvarDistribuicao(distribuicaoSelecionada);
        formalizacaoService.salvarFormalizacao(formalizacao);
    }

    private void salvarDistribuicao(DistribuicaoFormalizacao distribuicaoSelecionada) {
        distribuicaoFormalizacaoPersistenceAdapter.salvarDistribuicao(distribuicaoSelecionada);
    }
}
