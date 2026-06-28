package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.TransacaoBolsaDto;
import br.com.abracocontabilidade.bolsa.service.TransacaoBolsaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TransacaoBolsaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransacaoBolsaService transacaoBolsaService;

    @Test
    void deveCriarTransacaoDeCompraComSucesso() throws Exception {
        TransacaoBolsaDto entrada = new TransacaoBolsaDto(null, "PETR4", 100, new BigDecimal("28.50"), "COMPRA", LocalDateTime.now());
        TransacaoBolsaDto saida = new TransacaoBolsaDto(1L, "PETR4", 100, new BigDecimal("28.50"), "COMPRA", LocalDateTime.now());

        when(transacaoBolsaService.criarTransacao(entrada)).thenReturn(saida);

        mockMvc.perform(post("/api/bolsa/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.simbolo", equalTo("PETR4")))
                .andExpect(jsonPath("$.tipo", equalTo("COMPRA")));
    }

    @Test
    void deveRejeitarVendaSemSaldoSuficiente() throws Exception {
        TransacaoBolsaDto entrada = new TransacaoBolsaDto(null, "PETR4", 200, new BigDecimal("28.50"), "VENDA", LocalDateTime.now());

        when(transacaoBolsaService.criarTransacao(entrada))
                .thenThrow(new IllegalArgumentException("Saldo insuficiente para VENDA"));

        mockMvc.perform(post("/api/bolsa/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isBadRequest());
    }
}
