package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.HoldingCarteiraBolsaDto;
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
    void deveRetornar3HoldingsAoObterCarteira() throws Exception {
        List<HoldingCarteiraBolsaDto> carteira = java.util.Arrays.asList(
                new HoldingCarteiraBolsaDto("PETR4", 100, new BigDecimal("28.50"), new BigDecimal("30.25"), new BigDecimal("175.00"), new BigDecimal("6.14")),
                new HoldingCarteiraBolsaDto("VALE3", 50, new BigDecimal("60.00"), new BigDecimal("62.15"), new BigDecimal("107.50"), new BigDecimal("3.58")),
                new HoldingCarteiraBolsaDto("WEGE3", 200, new BigDecimal("35.20"), new BigDecimal("36.90"), new BigDecimal("340.00"), new BigDecimal("4.83"))
        );

        when(carteiraBolsaService.obterCarteira()).thenReturn(carteira);

        mockMvc.perform(get("/api/bolsa/carteira"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].simbolo", equalTo("PETR4")))
                .andExpect(jsonPath("$[1].simbolo", equalTo("VALE3")))
                .andExpect(jsonPath("$[2].simbolo", equalTo("WEGE3")));
    }
}
