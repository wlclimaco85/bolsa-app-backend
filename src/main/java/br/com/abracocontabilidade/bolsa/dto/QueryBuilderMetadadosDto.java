package br.com.abracocontabilidade.bolsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryBuilderMetadadosDto {

    private Map<String, List<ColunaDto>> tabelas;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ColunaDto {
        private String nome;
        private String tipo;
    }
}
