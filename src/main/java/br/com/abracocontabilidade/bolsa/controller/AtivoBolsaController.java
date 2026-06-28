package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.AtivoBolsaDto;
import br.com.abracocontabilidade.bolsa.service.AtivoBolsaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/lista")
    public ResponseEntity<Page<AtivoBolsaDto>> listarAtivos(
            Pageable pageable,
            @RequestParam(required = false) String setor,
            @RequestParam(required = false) String q) {
        return ResponseEntity.ok(ativoBolsaService.listarAtivos(pageable, setor, q));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtivoBolsaDto> obterAtivoPorId(@PathVariable Long id) {
        try {
            AtivoBolsaDto ativo = ativoBolsaService.obterAtivoPorId(id);
            return ResponseEntity.ok(ativo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
