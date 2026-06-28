package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.CarteiraBolsaDetalheDto;
import br.com.abracocontabilidade.bolsa.dto.CarteiraBolsaDto;
import br.com.abracocontabilidade.bolsa.service.CarteiraBolsaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/bolsa/carteira")
@RequiredArgsConstructor
public class CarteiraBolsaController {

    private final CarteiraBolsaService carteiraBolsaService;

    @GetMapping
    public ResponseEntity<List<CarteiraBolsaDto>> obterCarteira() {
        return ResponseEntity.ok(carteiraBolsaService.obterCarteira());
    }

    @GetMapping("/detalhe/{ativoId}")
    public ResponseEntity<CarteiraBolsaDetalheDto> obterDetalheCarteira(@PathVariable Long ativoId) {
        try {
            CarteiraBolsaDetalheDto detalhe = carteiraBolsaService.obterDetalheCarteira(ativoId);
            return ResponseEntity.ok(detalhe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
