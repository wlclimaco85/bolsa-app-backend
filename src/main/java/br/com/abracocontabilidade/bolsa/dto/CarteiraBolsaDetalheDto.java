package br.com.abracocontabilidade.bolsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraBolsaDetalheDto {
    private Long ativoId;
    private String ticker;
    private String nome;
    private Integer quantidade;
    private BigDecimal precoMedio;
    private BigDecimal precoAtual;
    private BigDecimal gainPercentual;
    private List<HistoricoTransacaoDto> historico;
    private Map<String, Integer> distribuicaoCorretora;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HistoricoTransacaoDto {
        private Integer quantidade;
        private BigDecimal preco;
        private String tipo;
        private LocalDateTime dataHora;
    }
}
