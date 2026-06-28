package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.SugestaoAtivoBolsaDto;
import br.com.abracocontabilidade.bolsa.service.SugestoesBolsaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SugestoesBolsaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SugestoesBolsaService sugestoesBolsaService;

    @Test
    void deveRetornar10SugestoesComLimitePadrao() throws Exception {
        List<SugestaoAtivoBolsaDto> sugestoes = java.util.Arrays.asList(
                new SugestaoAtivoBolsaDto("MGLU3", "Magazine Luiza", new BigDecimal("4.25"), "Recuperação de tendência de longo prazo", new BigDecimal("78.50")),
                new SugestaoAtivoBolsaDto("ASAI3", "Asaí Atacadista", new BigDecimal("14.80"), "Crescimento do segmento de varejo", new BigDecimal("82.00")),
                new SugestaoAtivoBolsaDto("MBLY3", "Mobilya", new BigDecimal("8.90"), "Potencial de expansão doméstica", new BigDecimal("65.25")),
                new SugestaoAtivoBolsaDto("VALE3", "Vale S.A.", new BigDecimal("58.40"), "Trending em commodities", new BigDecimal("85.00")),
                new SugestaoAtivoBolsaDto("PETR4", "Petrobras", new BigDecimal("32.10"), "Faltam no portfolio - dividendos", new BigDecimal("76.75")),
                new SugestaoAtivoBolsaDto("WEGE3", "WEG S.A.", new BigDecimal("97.80"), "Diversificação - engenharia", new BigDecimal("88.50")),
                new SugestaoAtivoBolsaDto("ITUB4", "Itaú Unibanco", new BigDecimal("27.50"), "Faltam no portfolio - setor financeiro", new BigDecimal("79.25")),
                new SugestaoAtivoBolsaDto("B3SA3", "B3 S.A.", new BigDecimal("12.65"), "Trending - infraestrutura", new BigDecimal("81.00")),
                new SugestaoAtivoBolsaDto("LREN3", "Lojas Renner", new BigDecimal("21.90"), "Recuperação pós-queda", new BigDecimal("72.40")),
                new SugestaoAtivoBolsaDto("BBAS3", "Banco do Brasil", new BigDecimal("41.35"), "Faltam no portfolio - financeiro estatal", new BigDecimal("74.80"))
        );

        when(sugestoesBolsaService.obterSugestoes(eq(10))).thenReturn(sugestoes);

        mockMvc.perform(get("/api/bolsa/sugestoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].simbolo", equalTo("MGLU3")))
                .andExpect(jsonPath("$[9].simbolo", equalTo("BBAS3")));
    }

    @Test
    void deveRetornarSugestoesComLimitePersonalizado() throws Exception {
        List<SugestaoAtivoBolsaDto> sugestoes = java.util.Arrays.asList(
                new SugestaoAtivoBolsaDto("MGLU3", "Magazine Luiza", new BigDecimal("4.25"), "Recuperação de tendência de longo prazo", new BigDecimal("78.50")),
                new SugestaoAtivoBolsaDto("ASAI3", "Asaí Atacadista", new BigDecimal("14.80"), "Crescimento do segmento de varejo", new BigDecimal("82.00")),
                new SugestaoAtivoBolsaDto("MBLY3", "Mobilya", new BigDecimal("8.90"), "Potencial de expansão doméstica", new BigDecimal("65.25"))
        );

        when(sugestoesBolsaService.obterSugestoes(eq(3))).thenReturn(sugestoes);

        mockMvc.perform(get("/api/bolsa/sugestoes?limite=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].simbolo", equalTo("MGLU3")))
                .andExpect(jsonPath("$[2].simbolo", equalTo("MBLY3")));
    }
}
