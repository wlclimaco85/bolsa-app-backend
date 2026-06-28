package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.AtivoBolsaDto;
import br.com.abracocontabilidade.bolsa.service.AtivoBolsaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AtivoBolsaDetalheControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtivoBolsaService ativoBolsaService;

    @Test
    void deveRetornarAtivoPorId() throws Exception {
        AtivoBolsaDto ativo = new AtivoBolsaDto("PETR4", "Petróbras", new BigDecimal("30.25"), new BigDecimal("2.15"), 15000000L, "Energia");

        when(ativoBolsaService.obterAtivoPorId(1L))
                .thenReturn(ativo);

        mockMvc.perform(get("/api/bolsa/ativos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.simbolo", equalTo("PETR4")))
                .andExpect(jsonPath("$.nome", equalTo("Petróbras")))
                .andExpect(jsonPath("$.setor", equalTo("Energia")))
                .andExpect(jsonPath("$.preco", equalTo(30.25)));
    }

    @Test
    void deveRetornar404QuandoAtivoNaoExiste() throws Exception {
        when(ativoBolsaService.obterAtivoPorId(999L))
                .thenThrow(new IllegalArgumentException("Ativo não encontrado"));

        mockMvc.perform(get("/api/bolsa/ativos/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarTodosOsCamposDoAtivo() throws Exception {
        AtivoBolsaDto ativo = new AtivoBolsaDto(
                "VALE3",
                "Vale",
                new BigDecimal("62.15"),
                new BigDecimal("1.50"),
                8500000L,
                "Mineração"
        );

        when(ativoBolsaService.obterAtivoPorId(2L))
                .thenReturn(ativo);

        mockMvc.perform(get("/api/bolsa/ativos/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.simbolo", equalTo("VALE3")))
                .andExpect(jsonPath("$.nome", equalTo("Vale")))
                .andExpect(jsonPath("$.preco", equalTo(62.15)))
                .andExpect(jsonPath("$.variacao24h", equalTo(1.50)))
                .andExpect(jsonPath("$.volume", equalTo(8500000)))
                .andExpect(jsonPath("$.setor", equalTo("Mineração")));
    }
}
