package br.com.formalizacaobackoffice.controller;

import br.com.formalizacaobackoffice.dto.DistribuicaoFormalizacaoDto;
import br.com.formalizacaobackoffice.dto.TipoFormalizacaoDto;
import br.com.formalizacaobackoffice.service.DistribuicaoFormalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("distribuicao_formalizacao")
public class DistribuicaoFormalizacaoController {

    @Autowired
    private DistribuicaoFormalizacaoService distribuicaoFormalizacaoService;

    @GetMapping
    public ResponseEntity<?> alterarPorcentagemDaDistribicao(@RequestParam(name = "nome_processo") String nomeProcesso,
                                                             @RequestParam(name = "nome_origem") String nomeOrigem,
                                                             @RequestParam(name = "nome_segmentacao") String nomeSegmentacao) {
        TipoFormalizacaoDto tipoFormalizacaoDto = new TipoFormalizacaoDto(nomeProcesso, nomeOrigem, nomeSegmentacao);
        List<DistribuicaoFormalizacaoDto> distribuicaoFormalizacaoLista = distribuicaoFormalizacaoService.buscarListaDistribuicaoFormalizacaoProTipoFormalizacao(tipoFormalizacaoDto);
        return ResponseEntity.ok(distribuicaoFormalizacaoLista);
    }

    @PutMapping
    public ResponseEntity<?> alterarPorcentagemDaDistribuicao(@RequestBody List<DistribuicaoFormalizacaoDto> distribuicaoFormalizacaoListaDto) {
        List<DistribuicaoFormalizacaoDto> distribuicaoFormalizacaoLista = distribuicaoFormalizacaoService.alterarPorcentagemDeListaDeDistribuicaoFormalizacao(distribuicaoFormalizacaoListaDto);
        return ResponseEntity.ok(distribuicaoFormalizacaoLista);
    }
}
