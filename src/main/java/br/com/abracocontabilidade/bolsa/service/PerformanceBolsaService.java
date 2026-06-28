package br.com.abracocontabilidade.bolsa.service;

import br.com.abracocontabilidade.bolsa.dto.PerformanceMemesialDto;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceBolsaService {

    public List<PerformanceMemesialDto> obterPerformance() {
        List<PerformanceMemesialDto> performance = new ArrayList<>();

        performance.add(new PerformanceMemesialDto("Janeiro", new BigDecimal("3.45"), new BigDecimal("1250.00")));
        performance.add(new PerformanceMemesialDto("Fevereiro", new BigDecimal("2.10"), new BigDecimal("750.00")));
        performance.add(new PerformanceMemesialDto("Março", new BigDecimal("5.20"), new BigDecimal("1875.50")));
        performance.add(new PerformanceMemesialDto("Abril", new BigDecimal("-1.50"), new BigDecimal("-540.00")));
        performance.add(new PerformanceMemesialDto("Maio", new BigDecimal("4.80"), new BigDecimal("1730.00")));
        performance.add(new PerformanceMemesialDto("Junho", new BigDecimal("2.35"), new BigDecimal("845.25")));

        return performance;
    }
}
