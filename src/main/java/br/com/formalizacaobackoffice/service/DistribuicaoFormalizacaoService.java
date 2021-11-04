package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.dto.TipoFormalizacaoDto;
import br.com.formalizacaobackoffice.dto.DistribuicaoFormalizacaoDto;
import br.com.formalizacaobackoffice.exception.DistribuicaoException;
import br.com.formalizacaobackoffice.mapper.DistribuicaoFormalizacaoMapper;
import br.com.formalizacaobackoffice.model.DistribuicaoFormalizacao;
import br.com.formalizacaobackoffice.model.Formalizacao;
import br.com.formalizacaobackoffice.model.TipoFormalizacao;
import br.com.formalizacaobackoffice.persistence.adapter.DistribuicaoFormalizacaoPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistribuicaoFormalizacaoService {
    @Autowired
    private DistribuicaoFormalizacaoPersistenceAdapter distribuicaoFormalizacaoPersistenceAdapter;
    @Autowired
    private FormalizadorService formalizadorService;
    @Autowired
    private FormalizacaoService formalizacaoService;
    @Autowired
    private TipoFormalizacaoService tipoFormalizacaoService;
    @Autowired
    private DistribuicaoFormalizacaoMapper distribuicaoFormalizacaoMapper;

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
        formalizacao.alterarStatus("ENVIADO");
        formalizacao.getObjetoAnaliseFormalizacaoLista().forEach(objeto -> objeto.alterarStatus("ENVIADO"));
        salvarDistribuicao(distribuicaoSelecionada);
        formalizacaoService.salvarFormalizacao(formalizacao);
    }

    private void salvarDistribuicao(DistribuicaoFormalizacao distribuicaoSelecionada) {
        distribuicaoFormalizacaoPersistenceAdapter.salvarDistribuicao(distribuicaoSelecionada);
    }

    public List<DistribuicaoFormalizacaoDto> buscarListaDistribuicaoFormalizacaoProTipoFormalizacao(TipoFormalizacaoDto tipoFormalizacaoDto) {
        TipoFormalizacao tipoFormalizacao = tipoFormalizacaoService.buscarTipoFormalizacaoPelosNomesDasCategorias(
                tipoFormalizacaoDto.getNomeProcesso(), tipoFormalizacaoDto.getNomeOrigem(), tipoFormalizacaoDto.getNomeSegmentacao());

        List<DistribuicaoFormalizacao> distribuicaoFormalizacaoLista = distribuicaoFormalizacaoPersistenceAdapter.buscarDistribuicaoFormalizacaoPorTipoFormalizacao(tipoFormalizacao);

        return distribuicaoFormalizacaoMapper.converterParaDto(distribuicaoFormalizacaoLista);
    }

    public List<DistribuicaoFormalizacaoDto> alterarPorcentagemDeListaDeDistribuicaoFormalizacao(List<DistribuicaoFormalizacaoDto> distribuicaoFormalizacaoListaDto) {
        List<TipoFormalizacao> tipoFormalizacaoLista = distribuicaoFormalizacaoListaDto.stream()
                .map(distribuicaoDto -> {
                    return tipoFormalizacaoService.buscarTipoFormalizacaoPelosNomesDasCategorias(
                            distribuicaoDto.getTipoFormalizacaoDto().getNomeProcesso(),
                            distribuicaoDto.getTipoFormalizacaoDto().getNomeOrigem(),
                            distribuicaoDto.getTipoFormalizacaoDto().getNomeSegmentacao());
                }).collect(Collectors.toList());

        List<DistribuicaoFormalizacao> distribuicaoFormalizacaoLista =
                distribuicaoFormalizacaoPersistenceAdapter.buscarDistribuicaoFormalizacaoPorListaDeTipoFormalizacao(tipoFormalizacaoLista);

        //Validar se precisa ter retorno mesmo
        List<DistribuicaoFormalizacao> distribuicaoFormalizacaoListaAlterada = alterarPorcentagem(distribuicaoFormalizacaoListaDto, distribuicaoFormalizacaoLista);
        validarPorcentagem(tipoFormalizacaoLista, distribuicaoFormalizacaoListaAlterada);
        distribuicaoFormalizacaoListaAlterada.forEach(DistribuicaoFormalizacao::zerarContadorDeDistribuicao);
        salvarListaDistribuicao(distribuicaoFormalizacaoListaAlterada);

        return distribuicaoFormalizacaoMapper.converterParaDto(distribuicaoFormalizacaoListaAlterada);
    }

    private List<DistribuicaoFormalizacao> alterarPorcentagem(List<DistribuicaoFormalizacaoDto> distribuicaoFormalizacaoListaDto, List<DistribuicaoFormalizacao> distribuicaoFormalizacaoLista) {
        distribuicaoFormalizacaoListaDto.forEach(distribuicaoDto -> {
            distribuicaoFormalizacaoLista.stream().filter(distribuicao -> distribuicao.getCodigoDistribuicaoFormalizacao() == distribuicaoDto.getCodigoDistribuicaoFormalizacao())
                    .findFirst().orElseThrow(() -> new DistribuicaoException("Distribuicao Formalizacao com id " + distribuicaoDto.getCodigoDistribuicaoFormalizacao() + " nao encontrado na base de dados!"))
                    .atualizarPorcentagem(distribuicaoDto.getPorcentagemDeDistribuicao());
        });
        return distribuicaoFormalizacaoLista;
    }

    private void validarPorcentagem(List<TipoFormalizacao> tipoFormalizacaoLista, List<DistribuicaoFormalizacao> distribuicaoFormalizacaoLista) {
        tipoFormalizacaoLista.forEach(tipoFormalizacao -> {
            double somaPorcentagem = distribuicaoFormalizacaoLista.stream().filter(distribuicaoFormalizacao ->
                    //Validar se daria pra implementar o Comparator/Comparable ao inves de fazer essa validacao aqui
                    distribuicaoFormalizacao.getTipoFormalizacao().getProcesso().getNomeProcesso().equals(tipoFormalizacao.getProcesso().getNomeProcesso()) &&
                            distribuicaoFormalizacao.getTipoFormalizacao().getOrigem().getNomeOrigem().equals(tipoFormalizacao.getOrigem().getNomeOrigem()) &&
                            distribuicaoFormalizacao.getTipoFormalizacao().getSegmentacao().getNomeSegmentacao().equals(tipoFormalizacao.getSegmentacao().getNomeSegmentacao()))
                    .mapToDouble(DistribuicaoFormalizacao::getPorcentagemDeDistribuicao).sum();

            if (somaPorcentagem != 100)
                throw new DistribuicaoException("Valor diferente de 100 na soma das porcentagens das distribuicoes de tipo formalizacao: " + tipoFormalizacao);
        });
    }

    private void salvarListaDistribuicao(List<DistribuicaoFormalizacao> distribuicaoFormalizacaoLista) {
        distribuicaoFormalizacaoPersistenceAdapter.salvarListaDistribuicao(distribuicaoFormalizacaoLista);
    }
}
