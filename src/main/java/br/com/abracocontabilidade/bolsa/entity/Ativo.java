package br.com.abracocontabilidade.bolsa.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ativo", uniqueConstraints = {
    @UniqueConstraint(columnNames = "ticker", name = "uk_ativo_ticker")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String ticker;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 100)
    private String setor;

    @Column(name = "preco_atual", nullable = false, precision = 18, scale = 2)
    private BigDecimal precoAtual;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;
}
