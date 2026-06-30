package br.com.abracocontabilidade.bolsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class QueryBuilderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> obterMetadados() {
        Map<String, Object> metadados = new LinkedHashMap<>();

        String sql = "SELECT table_name, column_name, data_type " +
                     "FROM information_schema.columns " +
                     "WHERE table_schema = 'public' " +
                     "ORDER BY table_name, ordinal_position";

        try {
            jdbcTemplate.query(sql, resultSet -> {
                String tabelaNome = resultSet.getString("table_name");
                String colunaNome = resultSet.getString("column_name");
                String tipo = resultSet.getString("data_type");

                metadados.putIfAbsent(tabelaNome, new ArrayList<>());
                @SuppressWarnings("unchecked")
                List<Map<String, String>> colunas = (List<Map<String, String>>) metadados.get(tabelaNome);

                Map<String, String> coluna = new LinkedHashMap<>();
                coluna.put("nome", colunaNome);
                coluna.put("tipo", tipo);
                colunas.add(coluna);
            });
        } catch (Exception e) {
            return metadados;
        }

        return metadados;
    }
}
