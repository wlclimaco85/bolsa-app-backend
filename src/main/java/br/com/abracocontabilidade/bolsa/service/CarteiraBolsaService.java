package br.com.abracocontabilidade.bolsa.service;

import br.com.abracocontabilidade.bolsa.dto.CarteiraBolsaDetalheDto;
import br.com.abracocontabilidade.bolsa.dto.CarteiraBolsaDto;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarteiraBolsaService {

    public List<CarteiraBolsaDto> obterCarteira() {
        List<CarteiraBolsaDto> carteira = new ArrayList<>();

        carteira.add(new CarteiraBolsaDto(
                "PETR4",
                "Petróbras",
                100,
                new BigDecimal("28.50"),
                new BigDecimal("30.25"),
                new BigDecimal("6.14")
        ));

        carteira.add(new CarteiraBolsaDto(
                "VALE3",
                "Vale",
                50,
                new BigDecimal("60.00"),
                new BigDecimal("62.15"),
                new BigDecimal("3.58")
        ));

        carteira.add(new CarteiraBolsaDto(
                "WEGE3",
                "Weg",
                200,
                new BigDecimal("35.20"),
                new BigDecimal("36.90"),
                new BigDecimal("4.83")
        ));

        return carteira;
    }

    public CarteiraBolsaDetalheDto obterDetalheCarteira(Long ativoId) {
        if (ativoId == null || ativoId <= 0) {
            throw new IllegalArgumentException("ID do ativo deve ser maior que zero");
        }

        // Dados de exemplo para simulação
        Map<String, Object> ativos = new HashMap<>();
        ativos.put("1", new Object[]{"PETR4", "Petróbras", 100, new BigDecimal("28.50"), new BigDecimal("30.25")});
        ativos.put("2", new Object[]{"VALE3", "Vale", 50, new BigDecimal("60.00"), new BigDecimal("62.15")});
        ativos.put("3", new Object[]{"WEGE3", "Weg", 200, new BigDecimal("35.20"), new BigDecimal("36.90")});

        Object[] ativoData = (Object[]) ativos.get(String.valueOf(ativoId));
        if (ativoData == null) {
            throw new IllegalArgumentException("Ativo não encontrado");
        }

        String ticker = (String) ativoData[0];
        String nome = (String) ativoData[1];
        Integer quantidade = (Integer) ativoData[2];
        BigDecimal precoMedio = (BigDecimal) ativoData[3];
        BigDecimal precoAtual = (BigDecimal) ativoData[4];

        // Calcular ganho percentual
        BigDecimal gain = precoAtual.subtract(precoMedio)
                .divide(precoMedio, 4, java.math.RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"));

        // Histórico simulado (últimas 10)
        List<CarteiraBolsaDetalheDto.HistoricoTransacaoDto> historico = Arrays.asList(
                new CarteiraBolsaDetalheDto.HistoricoTransacaoDto(50, precoMedio, "COMPRA", LocalDateTime.now().minusDays(10)),
                new CarteiraBolsaDetalheDto.HistoricoTransacaoDto(50, precoMedio, "COMPRA", LocalDateTime.now().minusDays(8))
        );

        // Distribuição por corretora
        Map<String, Integer> distribuicao = new HashMap<>();
        distribuicao.put("XP Investimentos", quantidade / 2);
        distribuicao.put("BTG Pactual", quantidade / 2);

        return new CarteiraBolsaDetalheDto(
                ativoId,
                ticker,
                nome,
                quantidade,
                precoMedio,
                precoAtual,
                gain,
                historico,
                distribuicao
        );
    }
}
