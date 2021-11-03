package br.com.formalizacaobackoffice;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TesteBuscaMenorData {
    public static void main(String[] args) {
        // se no iterator deixar n++ da recursividade
        List<Distribuicao> collect = Stream.iterate(1, n -> n < 10, n -> n + 1)
                //.limit(5) posso limitar com o .limit tambem
                .map(x -> new Distribuicao(LocalDateTime.now().plusDays(new Random().nextInt(200))))
                .collect(Collectors.toList());

        collect.forEach(x -> System.out.println(x.getDataHoraUltimaAtualizacaoPorcentagem()));

        Optional<Distribuicao> first = collect.stream().max(Comparator.comparing(Distribuicao::getDataHoraUltimaAtualizacaoPorcentagem));

        System.out.println("--------------------------------------------");
        first.ifPresent(distribuicao -> System.out.println(distribuicao.getDataHoraUltimaAtualizacaoPorcentagem()));
    }
}
