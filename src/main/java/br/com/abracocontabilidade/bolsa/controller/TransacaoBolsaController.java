package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.dto.TransacaoBolsaDto;
import br.com.abracocontabilidade.bolsa.service.TransacaoBolsaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bolsa/transacao")
@RequiredArgsConstructor
public class TransacaoBolsaController {

    private final TransacaoBolsaService transacaoBolsaService;

    @PostMapping
    public ResponseEntity<TransacaoBolsaDto> criarTransacao(@RequestBody TransacaoBolsaDto transacao) {
        TransacaoBolsaDto criada = transacaoBolsaService.criarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleValidationException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
