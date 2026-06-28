package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.CarteiraBolsaDetalheDto;
import br.com.abracocontabilidade.bolsa.service.CarteiraBolsaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarteiraBolsaDetalheControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarteiraBolsaService carteiraBolsaService;

    @Test
    void deveRetornarDetalheCarteiraPorAtivoId() throws Exception {
        CarteiraBolsaDetalheDto detalhe = criarDetalheCarteira();

        when(carteiraBolsaService.obterDetalheCarteira(1L))
                .thenReturn(detalhe);

        mockMvc.perform(get("/api/bolsa/carteira/detalhe/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ativoId", equalTo(1)))
                .andExpect(jsonPath("$.ticker", equalTo("PETR4")))
                .andExpect(jsonPath("$.quantidade", equalTo(100)))
                .andExpect(jsonPath("$.historico", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.distribuicaoCorretora", notNullValue()));
    }

    @Test
    void deveRetornarUltimas10TransacoesNoHistorico() throws Exception {
        CarteiraBolsaDetalheDto detalhe = criarDetalheCarteira();

        when(carteiraBolsaService.obterDetalheCarteira(1L))
                .thenReturn(detalhe);

        mockMvc.perform(get("/api/bolsa/carteira/detalhe/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.historico", hasSize(detalhe.getHistorico().size())));
    }

    @Test
    void deveRetornarDistribuicaoPorCorretora() throws Exception {
        CarteiraBolsaDetalheDto detalhe = criarDetalheCarteira();

        when(carteiraBolsaService.obterDetalheCarteira(1L))
                .thenReturn(detalhe);

        mockMvc.perform(get("/api/bolsa/carteira/detalhe/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.distribuicaoCorretora['XP Investimentos']", equalTo(50)))
                .andExpect(jsonPath("$.distribuicaoCorretora['BTG Pactual']", equalTo(50)));
    }

    @Test
    void deveRetornar404QuandoAtivoNaoExiste() throws Exception {
        when(carteiraBolsaService.obterDetalheCarteira(999L))
                .thenThrow(new IllegalArgumentException("Ativo não encontrado"));

        mockMvc.perform(get("/api/bolsa/carteira/detalhe/999"))
                .andExpect(status().isBadRequest());
    }

    private CarteiraBolsaDetalheDto criarDetalheCarteira() {
        Map<String, Integer> distribuicao = new HashMap<>();
        distribuicao.put("XP Investimentos", 50);
        distribuicao.put("BTG Pactual", 50);

        return new CarteiraBolsaDetalheDto(
                1L,
                "PETR4",
                "Petróbras",
                100,
                new BigDecimal("28.50"),
                new BigDecimal("30.25"),
                new BigDecimal("6.14"),
                Arrays.asList(
                        new CarteiraBolsaDetalheDto.HistoricoTransacaoDto(50, new BigDecimal("28.50"), "COMPRA", LocalDateTime.now()),
                        new CarteiraBolsaDetalheDto.HistoricoTransacaoDto(50, new BigDecimal("28.50"), "COMPRA", LocalDateTime.now())
                ),
                distribuicao
        );
    }
}
