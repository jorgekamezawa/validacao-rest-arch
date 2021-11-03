package br.com.formalizacaobackoffice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Distribuicao {
    private LocalDateTime dataHoraUltimaAtualizacaoPorcentagem;
}
