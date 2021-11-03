package br.com.formalizacaobackoffice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PalavraRepetida {
    public String palavra;
    public long contador;
}
