package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.dto.DistribuirFormalizacaoDto;
import br.com.formalizacaobackoffice.dto.EfetivarFormalizacaoDto;
import br.com.formalizacaobackoffice.dto.MotivoDevolucaoFormalizacaoDto;
import br.com.formalizacaobackoffice.exception.*;
import br.com.formalizacaobackoffice.model.*;
import br.com.formalizacaobackoffice.persistence.adapter.FormalizacaoPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    @Autowired
    private MotivoDevolucaoService motivoDevolucaoService;

    public void criarEDistribuirFormalizacao(DistribuirFormalizacaoDto distribuirFormalizacaoDto) {
        TipoFormalizacao tipoFormalizacao = tipoFormalizacaoService.buscarTipoFormalizacaoPelosNomesDasCategorias(
                distribuirFormalizacaoDto.getTipoFormalizacaoDto().getNomeProcesso(),
                distribuirFormalizacaoDto.getTipoFormalizacaoDto().getNomeOrigem(),
                distribuirFormalizacaoDto.getTipoFormalizacaoDto().getNomeSegmentacao());

        String codigoPessoa = distribuirFormalizacaoDto.getPessoaDto().getCodigoPessoa();
        if (pessoaService.validarSePessoaExiste(codigoPessoa))
            throw new DistribuicaoNotAcceptableException("Ja existe formalizacao realizada para o codigo pessoa: " + codigoPessoa);
        Pessoa pessoa = new Pessoa(codigoPessoa, distribuirFormalizacaoDto.getPessoaDto().getCodigoPessoaemporario());

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

    public void efetivarFormalizacao(EfetivarFormalizacaoDto efetivarFormalizacaoDto) {
        Formalizacao formalizacao = buscarFormalizacaoPorId(efetivarFormalizacaoDto.getCodigoFormalizacaoDto());
        validarEfetivacaoEAtribuirOsValoresDoDto(efetivarFormalizacaoDto, formalizacao);

        formalizacao.alterarStatus("EFETIVADO");
        salvarFormalizacao(formalizacao);
    }

    private void validarEfetivacaoEAtribuirOsValoresDoDto(EfetivarFormalizacaoDto efetivarFormalizacaoDto, Formalizacao formalizacao) {
        efetivarFormalizacaoDto.getObjetoAnaliseFormalizacaoDtoLista().forEach(objetoAnaliseDto -> {
            if (!objetoAnaliseDto.getStatusObjetoAnaliseDto().equals("APROVADO")) {
                throw new EfetivarNotAcceptableException("Formalizacao nao pode ser efetivada pois o objeto de analise " + objetoAnaliseDto.getNomeObjetoAnaliseDto()
                        + " contem motivos nao APROVADOS");
            }
            ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao = formalizacao.getObjetoAnaliseFormalizacaoLista().stream()
                    .filter(objetoAnalise -> objetoAnalise.getObjetoAnalise().getNomeObjetoAnalise().equals(objetoAnaliseDto.getNomeObjetoAnaliseDto()))
                    .findFirst().orElseThrow(() -> new EfetivarNotFoundException("Nome do Objeto Analise nao encontrado na base de dados!"));
            objetoAnaliseFormalizacao.alterarStatus(objetoAnaliseDto.getStatusObjetoAnaliseDto());

            if (objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista().isEmpty()) return;

            if (objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista().isEmpty())
                throw new EfetivarNotAcceptableException("Motivo de devolucao nao enviado para o objeto de analise " + objetoAnaliseDto.getNomeObjetoAnaliseDto());

            objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista().forEach(motivoDevolucaoDto -> {
                if (!motivoDevolucaoDto.getStatusMotivoDevolucaoDto().equals("RESOLVIDO")) {
                    throw new EfetivarNotAcceptableException("Formalizacao nao pode ser efetivada pois o objeto de analise " + objetoAnaliseDto.getNomeObjetoAnaliseDto()
                            + " contem motivos nao RESOLVIDOS!");
                }
                objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista().stream()
                        .filter(motivoDevolucaoFormalizacao -> motivoDevolucaoFormalizacao.getMotivoDevolucao().getNomeMotivoDevolucao().equals(motivoDevolucaoDto.getNomeMotivoDevolucaoDto()))
                        .findFirst().orElseThrow(() -> new EfetivarNotFoundException("Nome do Motivo Devolucao nao encontrado na base de dados!"))
                        .alterarStatus(motivoDevolucaoDto.getStatusMotivoDevolucaoDto());
            });
        });
    }

    public void devolverFormalizacao(EfetivarFormalizacaoDto efetivarFormalizacaoDto) {
        Formalizacao formalizacao = buscarFormalizacaoPorId(efetivarFormalizacaoDto.getCodigoFormalizacaoDto());
        validarDevolucaoEAtribuirOsValoresDoDto(efetivarFormalizacaoDto, formalizacao);

        formalizacao.alterarStatus("DEVOLVIDO");
        salvarFormalizacao(formalizacao);
    }

    private void validarDevolucaoEAtribuirOsValoresDoDto(EfetivarFormalizacaoDto efetivarFormalizacaoDto, Formalizacao formalizacao) {
        boolean validacaoStatusObjetoDto = efetivarFormalizacaoDto.getObjetoAnaliseFormalizacaoDtoLista().stream()
                .anyMatch(objetoAnaliseDto -> objetoAnaliseDto.getStatusObjetoAnaliseDto().equals("DEVOLVIDO"));
        if (!validacaoStatusObjetoDto)
            throw new DevolverFormalizacaoNotAcceptableException("Formalizacao nao pode ser devolvida pois nao existem objetos de analise com status DEVOLVIDO");

        efetivarFormalizacaoDto.getObjetoAnaliseFormalizacaoDtoLista().forEach(objetoAnaliseDto -> {
            ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao = formalizacao.getObjetoAnaliseFormalizacaoLista().stream()
                    .filter(objetoAnalise -> objetoAnalise.getObjetoAnalise().getNomeObjetoAnalise().equals(objetoAnaliseDto.getNomeObjetoAnaliseDto()))
                    .findFirst().orElseThrow(() -> new EfetivarNotFoundException("Nome do Objeto Analise enviado nao encontrado na base de dados!"));
            objetoAnaliseFormalizacao.alterarStatus(objetoAnaliseDto.getStatusObjetoAnaliseDto());

            if (objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista().isEmpty()) {
                if (objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista().isEmpty()) {
                    throw new DevolverFormalizacaoNotAcceptableException("Nao foi enviado motivo de devolucao necessario para o Objeto de Analise");
                }
                return;
            }

            objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista().forEach(motivoDevolucaoDto -> {
                if (objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista() == null) {
                    validarECriarNovoMotivoDevolucaoFormalizacao(motivoDevolucaoDto, objetoAnaliseFormalizacao);
                    return;
                }

                Optional<MotivoDevolucaoFormalizacao> motivoDevolucaoFormalizacao = objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista().stream()
                        .filter(motivoDevolucao -> motivoDevolucao.getMotivoDevolucao().getNomeMotivoDevolucao().equals(motivoDevolucaoDto.getNomeMotivoDevolucaoDto()))
                        .findFirst();
                if (motivoDevolucaoFormalizacao.isEmpty()) {
                    validarECriarNovoMotivoDevolucaoFormalizacao(motivoDevolucaoDto, objetoAnaliseFormalizacao);
                    return;
                }
                motivoDevolucaoFormalizacao.get().alterarStatus(motivoDevolucaoDto.getStatusMotivoDevolucaoDto());
            });
        });
    }

    public void validarECriarNovoMotivoDevolucaoFormalizacao(MotivoDevolucaoFormalizacaoDto motivoDevolucaoFormalizacaoDto,
                                                             ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao) {
        MotivoDevolucao motivoDevolucao = motivoDevolucaoService.buscarMotivoDevolucaoPorNome(motivoDevolucaoFormalizacaoDto.getNomeMotivoDevolucaoDto());
        MotivoDevolucaoFormalizacao motivoDevolucaoFormalizacao = new MotivoDevolucaoFormalizacao(
                motivoDevolucao,
                motivoDevolucaoFormalizacaoDto.getStatusMotivoDevolucaoDto()
        );
        objetoAnaliseFormalizacao.adicionarMotivoDevolucaoFormalizacao(motivoDevolucaoFormalizacao);
    }
}
