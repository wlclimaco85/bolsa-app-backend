package br.com.abracocontabilidade.bolsa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtivoDto {
    private Long id;
    private String ticker;
    private String nome;
    private String setor;
    private BigDecimal precoAtual;
    private LocalDateTime dataAtualizacao;
}
