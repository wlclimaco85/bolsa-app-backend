package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.AtivoBolsaDto;
import br.com.abracocontabilidade.bolsa.service.AtivoBolsaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/bolsa/ativos")
@RequiredArgsConstructor
public class AtivoBolsaController {

    private final AtivoBolsaService ativoBolsaService;

    @GetMapping
    public ResponseEntity<List<AtivoBolsaDto>> obterAtivos() {
        return ResponseEntity.ok(ativoBolsaService.obterAtivos());
    }
}
