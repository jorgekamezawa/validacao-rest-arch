package br.com.formalizacaobackoffice.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface FormatadorDeDataUtil {
    static String formatarDataHora(LocalDateTime dataHora) {
        return dataHora.format(DateTimeFormatter.ofPattern("dd//MM/yyyy HH:mm"));
    }
}
