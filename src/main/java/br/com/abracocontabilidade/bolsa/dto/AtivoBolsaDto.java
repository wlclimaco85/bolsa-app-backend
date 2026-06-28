package br.com.abracocontabilidade.bolsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtivoBolsaDto {
    private String simbolo;
    private String nome;
    private BigDecimal preco;
    private BigDecimal variacao24h;
    private Long volume;
    private String setor;
}
