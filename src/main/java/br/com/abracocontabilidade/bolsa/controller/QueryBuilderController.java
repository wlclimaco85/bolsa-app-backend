package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.service.QueryBuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/query-builder")
@RequiredArgsConstructor
public class QueryBuilderController {

    private final QueryBuilderService queryBuilderService;

    @GetMapping("/metadados")
    public ResponseEntity<Map<String, Object>> obterMetadados() {
        return ResponseEntity.ok(queryBuilderService.obterMetadados());
    }
}
