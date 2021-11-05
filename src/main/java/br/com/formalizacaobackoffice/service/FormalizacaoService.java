package br.com.formalizacaobackoffice.service;

import br.com.formalizacaobackoffice.dto.DistribuirFormalizacaoDto;
import br.com.formalizacaobackoffice.dto.EfetivarFormalizacaoDto;
import br.com.formalizacaobackoffice.dto.MotivoDevolucaoFormalizacaoDto;
import br.com.formalizacaobackoffice.exception.DevolverFormalizacaoException;
import br.com.formalizacaobackoffice.exception.EfetivarFormalizacaoException;
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

    public void efetivarFormalizacao(EfetivarFormalizacaoDto efetivarFormalizacaoDto) {
        Formalizacao formalizacao = buscarFormalizacaoPorId(efetivarFormalizacaoDto.getCodigoFormalizacaoDto());
        //Validar se precisa ter retorno mesmo
        Formalizacao formalizacaoValidada = validarEfetivacaoEAtribuirOsValoresDoDto(efetivarFormalizacaoDto, formalizacao);

        formalizacaoValidada.alterarStatus("EFETIVADO");
        salvarFormalizacao(formalizacaoValidada);
        //TODO: enviar formalizacao para o orquestrador(CO8), fazendo o parse para o schema avro definido pro CO8
        //TODO: fazer esse processo de atualizar a formalizacao com uma @Transaction pra nao precisar chamar o metodo de salvar
    }

    private Formalizacao validarEfetivacaoEAtribuirOsValoresDoDto(EfetivarFormalizacaoDto efetivarFormalizacaoDto, Formalizacao formalizacao) {
        efetivarFormalizacaoDto.getObjetoAnaliseFormalizacaoDtoLista().forEach(objetoAnaliseDto -> {
            if (!objetoAnaliseDto.getStatusObjetoAnaliseDto().equals("APROVADO")) {
                throw new EfetivarFormalizacaoException("Formalizacao nao pode ser efetivada pois o objeto de analise " + objetoAnaliseDto.getNomeObjetoAnaliseDto()
                        + "contem motivos nao RESOLVIDOS");
            }
            ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao = formalizacao.getObjetoAnaliseFormalizacaoLista().stream()
                    .filter(objetoAnalise -> objetoAnalise.getObjetoAnalise().getNomeObjetoAnalise().equals(objetoAnaliseDto.getNomeObjetoAnaliseDto()))
                    .findFirst().orElseThrow(() -> new EfetivarFormalizacaoException("Nome do Objeto Analise nao encontrado na base de dados!"));
            objetoAnaliseFormalizacao.alterarStatus(objetoAnaliseDto.getStatusObjetoAnaliseDto());

            if (objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista() == null) return;

            if (objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista() == null)
                throw new EfetivarFormalizacaoException("Motivo de devolucao nao enviado para o objeto de analise " + objetoAnaliseDto.getNomeObjetoAnaliseDto());

            objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista().forEach(motivoDevolucaoDto -> {
                if (!motivoDevolucaoDto.getStatusMotivoDevolucaoDto().equals("RESOLVIDO")) {
                    throw new EfetivarFormalizacaoException("Formalizacao nao pode ser efetivada pois o objeto de analise " + objetoAnaliseDto.getNomeObjetoAnaliseDto()
                            + " contem motivos nao RESOLVIDOS!");
                }
                objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista().stream()
                        .filter(motivoDevolucaoFormalizacao -> motivoDevolucaoFormalizacao.getMotivoDevolucao().getNomeMotivoDevolucao().equals(motivoDevolucaoDto.getNomeMotivoDevolucaoDto()))
                        .findFirst().orElseThrow(() -> new EfetivarFormalizacaoException("Nome do Motivo Devolucao nao encontrado na base de dados!"))
                        .alterarStatus(motivoDevolucaoDto.getStatusMotivoDevolucaoDto());
            });
        });
        return formalizacao;
    }

    public void devolverFormalizacao(EfetivarFormalizacaoDto efetivarFormalizacaoDto) {
        Formalizacao formalizacao = buscarFormalizacaoPorId(efetivarFormalizacaoDto.getCodigoFormalizacaoDto());
        //Validar se precisa ter retorno mesmo
        Formalizacao formalizacaoValidada = validarDevolucaoEAtribuirOsValoresDoDto(efetivarFormalizacaoDto, formalizacao);

        formalizacaoValidada.alterarStatus("DEVOLVIDO");
        salvarFormalizacao(formalizacaoValidada);
        //TODO: enviar formalizacao para o orquestrador
    }

    private Formalizacao validarDevolucaoEAtribuirOsValoresDoDto(EfetivarFormalizacaoDto efetivarFormalizacaoDto, Formalizacao formalizacao) {
        boolean validacaoStatusObjetoDto = efetivarFormalizacaoDto.getObjetoAnaliseFormalizacaoDtoLista().stream()
                .anyMatch(objetoAnaliseDto -> objetoAnaliseDto.getStatusObjetoAnaliseDto().equals("DEVOLVIDO"));
        if (!validacaoStatusObjetoDto)
            throw new DevolverFormalizacaoException("Formalizacao nao pode ser devolvida pois nao existem objetos de analise com status DEVOLVIDO");

        efetivarFormalizacaoDto.getObjetoAnaliseFormalizacaoDtoLista().forEach(objetoAnaliseDto -> {
            ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao = formalizacao.getObjetoAnaliseFormalizacaoLista().stream()
                    .filter(objetoAnalise -> objetoAnalise.getObjetoAnalise().getNomeObjetoAnalise().equals(objetoAnaliseDto.getNomeObjetoAnaliseDto()))
                    .findFirst().orElseThrow(() -> new EfetivarFormalizacaoException("Nome do Objeto Analise enviado nao encontrado na base de dados!"));
            objetoAnaliseFormalizacao.alterarStatus(objetoAnaliseDto.getStatusObjetoAnaliseDto());

            if (objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista() == null) {
                if (objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista() != null) {
                    throw new DevolverFormalizacaoException("Nao foi enviado motivo de devolucao necessario para o Objeto de Analise");
                }
                return;
            }

            objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista().forEach(motivoDevolucaoDto -> {
                if (objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista() == null) {
                    validarECriarNovoMotivoDevolucaoFormalizacao(motivoDevolucaoDto, objetoAnaliseFormalizacao);
                    return;
                }
//                objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista().stream()
//                        .filter(motivoDevolucao -> motivoDevolucao.getMotivoDevolucao().getNomeMotivoDevolucao().equals(motivoDevolucaoDto.getNomeMotivoDevolucaoDto()))
//                        .findFirst().orElse(validarECriarNovoMotivoDevolucaoFormalizacao(motivoDevolucaoDto, objetoAnaliseFormalizacao))
//                        .alterarStatus(motivoDevolucaoDto.getStatusMotivoDevolucaoDto());

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
        return formalizacao;
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


//    private Formalizacao validarDevolucaoEAtribuirOsValoresDoDtoOLD(EfetivarFormalizacaoDto efetivarFormalizacaoDto, Formalizacao formalizacao) {
//        boolean validacaoStatusObjeto = efetivarFormalizacaoDto.getObjetoAnaliseFormalizacaoDtoLista().stream()
//                .anyMatch(objetoAnaliseDto -> objetoAnaliseDto.getNomeObjetoAnaliseDto().equals("DEVOLVIDO"));
//        if (!validacaoStatusObjeto)
//            throw new DevolverFormalizacaoException("Formalizacao nao pode ser devolvida pois nao existem objetos de analise com status DEVOLVIDO");
//
//        efetivarFormalizacaoDto.getObjetoAnaliseFormalizacaoDtoLista().stream()
//                .filter(objetoAnaliseDto -> objetoAnaliseDto.getNomeObjetoAnaliseDto().equals("DEVOLVIDO"))
//                .forEach(objetoAnaliseDto -> {
//                    ObjetoAnaliseFormalizacao objetoAnaliseFormalizacao = formalizacao.getObjetoAnaliseFormalizacaoLista().stream()
//                            .filter(objetoAnalise -> objetoAnalise.getObjetoAnalise().getNomeObjetoAnalise().equals(objetoAnaliseDto.getNomeObjetoAnaliseDto()))
//                            .findFirst().orElseThrow(() -> new EfetivarFormalizacaoException("Nome do Objeto Analise nao encontrado na base de dados!"));
//                    objetoAnaliseFormalizacao.alterarStatus(objetoAnaliseDto.getStatusObjetoAnaliseDto());
//
//                    boolean validacaoStatusMotivo = objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista().stream().anyMatch(motivoDevolucaoDto -> motivoDevolucaoDto.getStatusMotivoDevolucaoDto().equals("RECUSADO"));
//                    if (!validacaoStatusMotivo)
//                        throw new DevolverFormalizacaoException("Formalizacao nao pode ser devolvida pois nao existem motivos RECUSADO");
//
//                    objetoAnaliseDto.getMotivoDevolucaoFormalizacaoDtoLista().stream()
//                            .filter(motivoDevolucaoDto -> motivoDevolucaoDto.getStatusMotivoDevolucaoDto().equals("RECUSADO"))
//                            .forEach(motivoDevolucaoDto -> {
//                                if (objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista() == null) {
//                                    MotivoDevolucao motivoDevolucao = motivoDevolucaoService.buscarMotivoDevolucaoPorNome(motivoDevolucaoDto.getNomeMotivoDevolucaoDto());
//                                    MotivoDevolucaoFormalizacao motivoDevolucaoFormalizacao = new MotivoDevolucaoFormalizacao(
//                                            motivoDevolucao,
//                                            "RECUSADO"
//                                    );
//                                    objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista().add(motivoDevolucaoFormalizacao);
//                                    return;
//                                }
//                                objetoAnaliseFormalizacao.getMotivoDevolucaoFormalizacaoLista().stream()
//                                        .filter(motivoDevolucao -> motivoDevolucao.getMotivoDevolucao().getNomeMotivoDevolucao().equals(motivoDevolucaoDto.getNomeMotivoDevolucaoDto()))
//                                        .findFirst().orElseThrow(() -> new DevolverFormalizacaoException("Nome do Motivo Devolucao nao encontrado na base de dados!"))
//                                        .alterarStatus(motivoDevolucaoDto.getStatusMotivoDevolucaoDto());
//                            });
//                });
//        return formalizacao;
//    }
}
