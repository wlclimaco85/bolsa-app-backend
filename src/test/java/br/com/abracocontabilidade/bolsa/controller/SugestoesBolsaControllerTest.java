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
    void deveRetornar3SugestoesDeAtivos() throws Exception {
        List<SugestaoAtivoBolsaDto> sugestoes = java.util.Arrays.asList(
                new SugestaoAtivoBolsaDto("MGLU3", "Magazine Luiza", new BigDecimal("4.25"), "Recuperação de tendência de longo prazo", new BigDecimal("78.50")),
                new SugestaoAtivoBolsaDto("ASAI3", "Asaí Atacadista", new BigDecimal("14.80"), "Crescimento do segmento de varejo", new BigDecimal("82.00")),
                new SugestaoAtivoBolsaDto("MBLY3", "Mobilya", new BigDecimal("8.90"), "Potencial de expansão doméstica", new BigDecimal("65.25"))
        );

        when(sugestoesBolsaService.obterSugestoes()).thenReturn(sugestoes);

        mockMvc.perform(get("/api/bolsa/sugestoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].simbolo", equalTo("MGLU3")))
                .andExpect(jsonPath("$[2].simbolo", equalTo("MBLY3")));
    }
}
