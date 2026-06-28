package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.SugestaoAtivoBolsaDto;
import br.com.abracocontabilidade.bolsa.service.SugestoesBolsaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/bolsa/sugestoes")
@RequiredArgsConstructor
public class SugestoesBolsaController {

    private final SugestoesBolsaService sugestoesBolsaService;

    @GetMapping
    public ResponseEntity<List<SugestaoAtivoBolsaDto>> obterSugestoes() {
        return ResponseEntity.ok(sugestoesBolsaService.obterSugestoes());
    }
}
