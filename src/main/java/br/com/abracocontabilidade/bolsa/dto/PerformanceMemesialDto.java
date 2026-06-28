package br.com.abracocontabilidade.bolsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceMemesialDto {
    private String mes;
    private BigDecimal percentualRetorno;
    private BigDecimal ganhoPerda;
}
