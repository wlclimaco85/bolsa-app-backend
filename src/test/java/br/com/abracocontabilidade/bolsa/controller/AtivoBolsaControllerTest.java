package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.AtivoBolsaDto;
import br.com.abracocontabilidade.bolsa.service.AtivoBolsaService;
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
class AtivoBolsaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtivoBolsaService ativoBolsaService;

    @Test
    void deveRetornar10AtivosAoObterAtivos() throws Exception {
        List<AtivoBolsaDto> ativos = java.util.Arrays.asList(
                new AtivoBolsaDto("PETR4", "Petróbras", new BigDecimal("30.25"), new BigDecimal("2.15"), 15000000L, "Energia"),
                new AtivoBolsaDto("VALE3", "Vale", new BigDecimal("62.15"), new BigDecimal("1.50"), 8500000L, "Mineração"),
                new AtivoBolsaDto("WEGE3", "Weg", new BigDecimal("36.90"), new BigDecimal("1.75"), 5200000L, "Indústria"),
                new AtivoBolsaDto("ITUB4", "Itaú Unibanco", new BigDecimal("28.45"), new BigDecimal("-0.50"), 12000000L, "Financeiro"),
                new AtivoBolsaDto("BBDC4", "Bradesco", new BigDecimal("25.60"), new BigDecimal("0.80"), 10500000L, "Financeiro"),
                new AtivoBolsaDto("ABEV3", "Ambev", new BigDecimal("13.25"), new BigDecimal("2.30"), 20000000L, "Bebidas"),
                new AtivoBolsaDto("JBSS3", "Jbs", new BigDecimal("22.10"), new BigDecimal("-1.20"), 3500000L, "Alimentos"),
                new AtivoBolsaDto("GGBR4", "Gerdau", new BigDecimal("18.50"), new BigDecimal("1.10"), 6800000L, "Siderurgia"),
                new AtivoBolsaDto("SUZB3", "Suzano", new BigDecimal("16.75"), new BigDecimal("0.45"), 4200000L, "Papel"),
                new AtivoBolsaDto("RENT3", "Localiza", new BigDecimal("19.90"), new BigDecimal("2.60"), 2500000L, "Aluguel")
        );

        when(ativoBolsaService.obterAtivos()).thenReturn(ativos);

        mockMvc.perform(get("/api/bolsa/ativos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].simbolo", equalTo("PETR4")))
                .andExpect(jsonPath("$[9].simbolo", equalTo("RENT3")));
    }
}
