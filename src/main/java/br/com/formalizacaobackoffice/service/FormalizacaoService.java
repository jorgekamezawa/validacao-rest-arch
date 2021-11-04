package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.dto.DistribuirFormalizacaoDto;
import br.com.formalizacaobackoffice.model.Formalizacao;
import br.com.formalizacaobackoffice.model.Pessoa;
import br.com.formalizacaobackoffice.model.TipoFormalizacao;
import br.com.formalizacaobackoffice.persistence.adapter.FormalizacaoPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormalizacaoService {
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
        TipoFormalizacao tipoFormalizacao = tipoFormalizacaoService.buscarTipoFormalizacaoPelosNomesDasCategorias(
                distribuirFormalizacaoDto.getTipoFormalizacaoDto().getNomeProcesso(),
                distribuirFormalizacaoDto.getTipoFormalizacaoDto().getNomeOrigem(),
                distribuirFormalizacaoDto.getTipoFormalizacaoDto().getNomeSegmentacao());

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

    public void alterarStatus() {
        List<Formalizacao> formalizacaoLista = formalizacaoPersistenceAdapter.buscarTodosFormalizacao();
        Formalizacao formalizacao = formalizacaoLista.stream().findFirst().get();
        formalizacao.alterarStatus("ALTERADO");
        salvarFormalizacao(formalizacao);
    }
}
