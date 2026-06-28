package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.PerformanceMemesialDto;
import br.com.abracocontabilidade.bolsa.service.PerformanceBolsaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/bolsa/performance")
@RequiredArgsConstructor
public class PerformanceBolsaController {

    private final PerformanceBolsaService performanceBolsaService;

    @GetMapping
    public ResponseEntity<List<PerformanceMemesialDto>> obterPerformance() {
        return ResponseEntity.ok(performanceBolsaService.obterPerformance());
    }
}
