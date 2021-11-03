package br.com.formalizacaobackoffice;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TesteSort {
    public static void main(String[] args) {
//        List<Integer> contador = Stream.iterate(1, n -> n + 1)
//                .limit(5)
//                .map(x -> new Random().nextInt(200))
//                .sorted(Comparator.comparing(Integer::intValue).reversed())
//                .collect(Collectors.toList());

        List<Integer> contador = Stream.iterate(1, n -> n + 1)
                .limit(5)
                .collect(Collectors.toList());

        contador.forEach(System.out::println);

        System.out.println("=============================================");

//        for (Integer cont : contador) {
//            if (cont == 3) {
//                return;
//            }
//            System.out.println(cont);
//        }

        boolean b = contador.stream().anyMatch(c -> {
            return c == 3;
        });

        int stringStream = contador.stream().filter(c -> {
            return c == 3;
        }).mapToInt(c -> c*2).sum();

        System.out.println(stringStream);

        System.out.println(contador.stream().max(Comparator.comparing(Integer::intValue)).get());
        System.out.println(contador.stream().min(Comparator.comparing(Integer::intValue)).get());


    }
}
