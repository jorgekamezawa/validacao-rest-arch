package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.dto.DistribuirFormalizacaoDto;
import br.com.formalizacaobackoffice.model.*;
import br.com.formalizacaobackoffice.persistence.adapter.FormalizacaoPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormalizacaoService {
    @Autowired
    private ProcessoService processoService;
    @Autowired
    private OrigemService origemService;
    @Autowired
    private SegmentacaoService segmentacaoService;
    @Autowired
    private TipoFormalizacaoService tipoFormalizacaoService;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private FormalizacaoPersistenceAdapter formalizacaoPersistenceAdapter;
    @Autowired
    private ObjetoAnaliseFormalizacaoService objetoAnaliseFormalizacaoService;
    @Autowired
    private DistribuicaoFormalizacaoService distribuicaoFormalizacaoService;

    public void criarEDistribuirFormalizacao(DistribuirFormalizacaoDto distribuirFormalizacaoDto) {
        Processo processo = processoService.buscarProcesso(distribuirFormalizacaoDto.getTipoFormalizacaoDto().getNomeProcesso());
        Origem origem = origemService.buscarOrigem(distribuirFormalizacaoDto.getTipoFormalizacaoDto().getNomeOrigem());
        Segmentacao segmentacao = segmentacaoService.buscarSegmentacao(distribuirFormalizacaoDto.getTipoFormalizacaoDto().getNomeSegmentacao());
        TipoFormalizacao tipoFormalizacao = tipoFormalizacaoService.buscarTipoFormalizacao(processo, origem, segmentacao);

        Pessoa pessoa = new Pessoa(distribuirFormalizacaoDto.getPessoaDto().getCodigoPessoa(), distribuirFormalizacaoDto.getPessoaDto().getCodigoPessoaemporario());

        Formalizacao formalizacao = new Formalizacao();
        formalizacao.criarFormalizacao(tipoFormalizacao, pessoa);

        objetoAnaliseFormalizacaoService.criarListaObjetoAnaliseFormalizacao(formalizacao, distribuirFormalizacaoDto.getImagemLista());
        distribuicaoFormalizacaoService.distribuirFormalizacao(formalizacao);
    }

    public void salvarFormalizacao(Formalizacao formalizacao) {
        formalizacaoPersistenceAdapter.salvarFormalizacao(formalizacao);
    }

    public Formalizacao buscarFormalizacaoPorId(String codigoFormalizacao) {
        return formalizacaoPersistenceAdapter.buscarFormalizacaoPorId(codigoFormalizacao);
    }
}
