package br.com.abracocontabilidade.bolsa.dto;

import br.com.abracocontabilidade.bolsa.entity.TipoTransacao;
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
public class TransacaoDto {
    private Long id;
    private Long usuarioId;
    private Long ativoId;
    private Integer quantidade;
    private BigDecimal preco;
    private LocalDateTime dataHora;
    private String corretora;
    private TipoTransacao tipo;
}
