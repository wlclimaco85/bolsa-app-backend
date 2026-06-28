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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TransacaoBolsaValidacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransacaoBolsaService transacaoBolsaService;

    @Test
    void deveRejeitarTransacaoComUsuarioIdNulo() throws Exception {
        TransacaoBolsaDto transacao = new TransacaoBolsaDto(null, null, "PETR4", 100, new BigDecimal("28.50"), "COMPRA", LocalDateTime.now());

        when(transacaoBolsaService.criarTransacao(transacao))
                .thenThrow(new IllegalArgumentException("Usuário ID é obrigatório"));

        mockMvc.perform(post("/api/bolsa/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRejeitarTransacaoComUsuarioIdInvalido() throws Exception {
        TransacaoBolsaDto transacao = new TransacaoBolsaDto(null, -1L, "PETR4", 100, new BigDecimal("28.50"), "COMPRA", LocalDateTime.now());

        when(transacaoBolsaService.criarTransacao(transacao))
                .thenThrow(new IllegalArgumentException("Usuário ID deve ser maior que zero"));

        mockMvc.perform(post("/api/bolsa/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRejeitarTransacaoComTipoInvalido() throws Exception {
        TransacaoBolsaDto transacao = new TransacaoBolsaDto(null, 1L, "PETR4", 100, new BigDecimal("28.50"), "INVALIDO", LocalDateTime.now());

        when(transacaoBolsaService.criarTransacao(transacao))
                .thenThrow(new IllegalArgumentException("Tipo de transação deve ser COMPRA ou VENDA"));

        mockMvc.perform(post("/api/bolsa/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveAceitarTransacaoDeCompraValida() throws Exception {
        TransacaoBolsaDto entrada = new TransacaoBolsaDto(null, 1L, "PETR4", 100, new BigDecimal("28.50"), "COMPRA", LocalDateTime.now());
        TransacaoBolsaDto saida = new TransacaoBolsaDto(1L, 1L, "PETR4", 100, new BigDecimal("28.50"), "COMPRA", LocalDateTime.now());

        when(transacaoBolsaService.criarTransacao(entrada)).thenReturn(saida);

        mockMvc.perform(post("/api/bolsa/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRejeitarVendaSemSaldoSuficiente() throws Exception {
        TransacaoBolsaDto transacao = new TransacaoBolsaDto(null, 1L, "PETR4", 200, new BigDecimal("28.50"), "VENDA", LocalDateTime.now());

        when(transacaoBolsaService.criarTransacao(transacao))
                .thenThrow(new IllegalArgumentException("Saldo insuficiente para VENDA"));

        mockMvc.perform(post("/api/bolsa/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRejeitarTransacaoComQuantidadeZero() throws Exception {
        TransacaoBolsaDto transacao = new TransacaoBolsaDto(null, 1L, "PETR4", 0, new BigDecimal("28.50"), "COMPRA", LocalDateTime.now());

        when(transacaoBolsaService.criarTransacao(transacao))
                .thenThrow(new IllegalArgumentException("Quantidade deve ser maior que zero"));

        mockMvc.perform(post("/api/bolsa/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRejeitarTransacaoComPrecoZero() throws Exception {
        TransacaoBolsaDto transacao = new TransacaoBolsaDto(null, 1L, "PETR4", 100, new BigDecimal("0"), "COMPRA", LocalDateTime.now());

        when(transacaoBolsaService.criarTransacao(transacao))
                .thenThrow(new IllegalArgumentException("Preço deve ser maior que zero"));

        mockMvc.perform(post("/api/bolsa/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isBadRequest());
    }
}
