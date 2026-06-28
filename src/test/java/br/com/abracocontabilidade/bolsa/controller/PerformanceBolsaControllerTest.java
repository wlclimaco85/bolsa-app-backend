package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.PerformanceMemesialDto;
import br.com.abracocontabilidade.bolsa.service.PerformanceBolsaService;
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
class PerformanceBolsaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerformanceBolsaService performanceBolsaService;

    @Test
    void deveRetornar6MesesDePerformance() throws Exception {
        List<PerformanceMemesialDto> performance = java.util.Arrays.asList(
                new PerformanceMemesialDto("Janeiro", new BigDecimal("3.45"), new BigDecimal("1250.00")),
                new PerformanceMemesialDto("Fevereiro", new BigDecimal("2.10"), new BigDecimal("750.00")),
                new PerformanceMemesialDto("Março", new BigDecimal("5.20"), new BigDecimal("1875.50")),
                new PerformanceMemesialDto("Abril", new BigDecimal("-1.50"), new BigDecimal("-540.00")),
                new PerformanceMemesialDto("Maio", new BigDecimal("4.80"), new BigDecimal("1730.00")),
                new PerformanceMemesialDto("Junho", new BigDecimal("2.35"), new BigDecimal("845.25"))
        );

        when(performanceBolsaService.obterPerformance()).thenReturn(performance);

        mockMvc.perform(get("/api/bolsa/performance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].mes", equalTo("Janeiro")))
                .andExpect(jsonPath("$[5].mes", equalTo("Junho")));
    }
}
