package br.com.abracocontabilidade.bolsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingCarteiraBolsaDto {
    private String simbolo;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoAtual;
    private BigDecimal ganhoPerda;
    private BigDecimal percentualGanho;
}
