package br.com.abracocontabilidade.bolsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoBolsaDto {
    private Long id;
    private Long usuarioId;
    private String simbolo;
    private Integer quantidade;
    private BigDecimal preco;
    private String tipo; // COMPRA, VENDA
    private LocalDateTime dataPregao;
}
