package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.AtivoBolsaDto;
import br.com.abracocontabilidade.bolsa.service.AtivoBolsaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    void deveRetornarPaginaComFiltroSetor() throws Exception {
        List<AtivoBolsaDto> ativos = Arrays.asList(
                new AtivoBolsaDto("PETR4", "Petróbras", new BigDecimal("30.25"), new BigDecimal("2.15"), 15000000L, "Energia"),
                new AtivoBolsaDto("VALE3", "Vale", new BigDecimal("62.15"), new BigDecimal("1.50"), 8500000L, "Energia")
        );
        Page<AtivoBolsaDto> page = new PageImpl<>(ativos);

        when(ativoBolsaService.listarAtivos(any(Pageable.class), eq("Energia"), any())).thenReturn(page);

        mockMvc.perform(get("/api/bolsa/ativos/lista?page=0&size=50&setor=Energia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].simbolo", equalTo("PETR4")))
                .andExpect(jsonPath("$.content[1].simbolo", equalTo("VALE3")));
    }

    @Test
    void deveRetornarPaginaComFiltroTicker() throws Exception {
        List<AtivoBolsaDto> ativos = Arrays.asList(
                new AtivoBolsaDto("PETR4", "Petróbras", new BigDecimal("30.25"), new BigDecimal("2.15"), 15000000L, "Energia")
        );
        Page<AtivoBolsaDto> page = new PageImpl<>(ativos);

        when(ativoBolsaService.listarAtivos(any(Pageable.class), any(), eq("PETR"))).thenReturn(page);

        mockMvc.perform(get("/api/bolsa/ativos/lista?page=0&size=50&q=PETR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].simbolo", equalTo("PETR4")));
    }
}
