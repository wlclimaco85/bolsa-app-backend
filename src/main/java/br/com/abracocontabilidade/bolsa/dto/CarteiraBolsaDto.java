package br.com.abracocontabilidade.bolsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraBolsaDto {
    private String ticker;
    private String nome;
    private Integer quantidade;
    private BigDecimal precoMedio;
    private BigDecimal valorAtual;
    private BigDecimal rendimentoPct;
}
