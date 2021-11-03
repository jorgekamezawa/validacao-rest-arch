package br.com.formalizacaobackoffice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TesteRepeticaoPalavra {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Jorge", "Livia", "Murillo", "Jorge", "Livia", "Kaue", "Jorge", "Murillo", "Livia", "Murillo", "Jorge", "Livia", "Jorge", "Kaue");

        List<PalavraRepetida> palavrasRepetidas = new ArrayList<>();
        strings.forEach(s -> {
            Optional<PalavraRepetida> first = palavrasRepetidas.stream().filter(p -> p.getPalavra().equalsIgnoreCase(s)).findFirst();
            if (first.isEmpty()) {
                palavrasRepetidas.add(new PalavraRepetida(s, 1));
                return;
            }
            first.get().contador++;
        });

        palavrasRepetidas.forEach(System.out::println);
    }
}
