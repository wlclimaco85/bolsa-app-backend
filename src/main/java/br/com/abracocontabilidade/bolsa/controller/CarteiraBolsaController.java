package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.HoldingCarteiraBolsaDto;
import br.com.abracocontabilidade.bolsa.service.CarteiraBolsaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/bolsa/carteira")
@RequiredArgsConstructor
public class CarteiraBolsaController {

    private final CarteiraBolsaService carteiraBolsaService;

    @GetMapping
    public ResponseEntity<List<HoldingCarteiraBolsaDto>> obterCarteira() {
        return ResponseEntity.ok(carteiraBolsaService.obterCarteira());
    }
}
