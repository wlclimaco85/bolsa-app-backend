package br.com.abracocontabilidade.bolsa.controller;

import br.com.abracocontabilidade.bolsa.service.QueryBuilderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class QueryBuilderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueryBuilderService queryBuilderService;

    @Test
    void testMetadadosRetorna200Ok() throws Exception {
        Map<String, Object> metadados = new HashMap<>();
        when(queryBuilderService.obterMetadados()).thenReturn(metadados);

        mockMvc.perform(get("/api/query-builder/metadados"))
                .andExpect(status().isOk());
    }

    @Test
    void testMetadadosEstrutura() throws Exception {
        Map<String, List<Map<String, String>>> metadados = new HashMap<>();
        List<Map<String, String>> colunas = new ArrayList<>();
        Map<String, String> coluna = new HashMap<>();
        coluna.put("nome", "id");
        coluna.put("tipo", "integer");
        colunas.add(coluna);
        metadados.put("tabela_teste", colunas);

        when(queryBuilderService.obterMetadados()).thenReturn((Map<String, Object>) (Object) metadados);

        mockMvc.perform(get("/api/query-builder/metadados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.tabela_teste", notNullValue()))
                .andExpect(jsonPath("$.tabela_teste[0].nome", equalTo("id")))
                .andExpect(jsonPath("$.tabela_teste[0].tipo", equalTo("integer")));
    }

    @Test
    void testMetadadosListaTabelas() throws Exception {
        Map<String, List<Map<String, String>>> metadados = new HashMap<>();
        List<Map<String, String>> colunasTab1 = new ArrayList<>();
        Map<String, String> colTab1 = new HashMap<>();
        colTab1.put("nome", "id");
        colTab1.put("tipo", "bigint");
        colunasTab1.add(colTab1);

        List<Map<String, String>> colunasTab2 = new ArrayList<>();
        Map<String, String> colTab2 = new HashMap<>();
        colTab2.put("nome", "nome");
        colTab2.put("tipo", "text");
        colunasTab2.add(colTab2);

        metadados.put("usuario", colunasTab1);
        metadados.put("empresa", colunasTab2);

        when(queryBuilderService.obterMetadados()).thenReturn((Map<String, Object>) (Object) metadados);

        mockMvc.perform(get("/api/query-builder/metadados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario", notNullValue()))
                .andExpect(jsonPath("$.empresa", notNullValue())
                );
    }

    @Test
    void testMetadadosColunasCamposObrigatorios() throws Exception {
        Map<String, List<Map<String, String>>> metadados = new HashMap<>();
        List<Map<String, String>> colunas = new ArrayList<>();
        Map<String, String> coluna = new HashMap<>();
        coluna.put("nome", "email");
        coluna.put("tipo", "character varying");
        colunas.add(coluna);
        metadados.put("usuario", colunas);

        when(queryBuilderService.obterMetadados()).thenReturn((Map<String, Object>) (Object) metadados);

        mockMvc.perform(get("/api/query-builder/metadados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario[0]", hasKey("nome")))
                .andExpect(jsonPath("$.usuario[0]", hasKey("tipo")));
    }
}
