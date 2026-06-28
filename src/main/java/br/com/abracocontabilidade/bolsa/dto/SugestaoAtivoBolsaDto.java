package br.com.abracocontabilidade.bolsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SugestaoAtivoBolsaDto {
    private String simbolo;
    private String nome;
    private BigDecimal preco;
    private String motivo;
    private BigDecimal confianca; // 0-100
    private String setor; // Setor da empresa (ex: Energia, Mineração, etc)
}
