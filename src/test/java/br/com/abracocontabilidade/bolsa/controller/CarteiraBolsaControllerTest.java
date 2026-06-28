package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.CarteiraBolsaDto;
import br.com.abracocontabilidade.bolsa.service.CarteiraBolsaService;
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
class CarteiraBolsaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarteiraBolsaService carteiraBolsaService;

    @Test
    void deveRetornarCarteiraVaziaAoObterCarteira() throws Exception {
        List<CarteiraBolsaDto> carteira = java.util.Collections.emptyList();

        when(carteiraBolsaService.obterCarteira()).thenReturn(carteira);

        mockMvc.perform(get("/api/bolsa/carteira"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void deveRetornar3AtivosComCarteiraMapeada() throws Exception {
        List<CarteiraBolsaDto> carteira = java.util.Arrays.asList(
                new CarteiraBolsaDto("PETR4", "Petróbras", 100, new BigDecimal("28.50"), new BigDecimal("30.25"), new BigDecimal("6.14")),
                new CarteiraBolsaDto("VALE3", "Vale", 50, new BigDecimal("60.00"), new BigDecimal("62.15"), new BigDecimal("3.58")),
                new CarteiraBolsaDto("WEGE3", "Weg", 200, new BigDecimal("35.20"), new BigDecimal("36.90"), new BigDecimal("4.83"))
        );

        when(carteiraBolsaService.obterCarteira()).thenReturn(carteira);

        mockMvc.perform(get("/api/bolsa/carteira"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].ticker", equalTo("PETR4")))
                .andExpect(jsonPath("$[0].nome", equalTo("Petróbras")))
                .andExpect(jsonPath("$[0].quantidade", equalTo(100)))
                .andExpect(jsonPath("$[0].precoMedio", equalTo(28.50)))
                .andExpect(jsonPath("$[0].valorAtual", equalTo(30.25)))
                .andExpect(jsonPath("$[0].rendimentoPct", equalTo(6.14)))
                .andExpect(jsonPath("$[1].ticker", equalTo("VALE3")))
                .andExpect(jsonPath("$[2].ticker", equalTo("WEGE3")));
    }
}
