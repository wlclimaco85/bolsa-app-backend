package br.com.abracocontabilidade.bolsa.service;

import br.com.abracocontabilidade.bolsa.dto.AtivoBolsaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtivoBolsaService {

    private List<AtivoBolsaDto> obterTodosAtivos() {
        List<AtivoBolsaDto> ativos = new ArrayList<>();
        ativos.add(new AtivoBolsaDto("PETR4", "Petróbras", new BigDecimal("30.25"), new BigDecimal("2.15"), 15000000L, "Energia"));
        ativos.add(new AtivoBolsaDto("VALE3", "Vale", new BigDecimal("62.15"), new BigDecimal("1.50"), 8500000L, "Mineração"));
        ativos.add(new AtivoBolsaDto("WEGE3", "Weg", new BigDecimal("36.90"), new BigDecimal("1.75"), 5200000L, "Indústria"));
        ativos.add(new AtivoBolsaDto("ITUB4", "Itaú Unibanco", new BigDecimal("28.45"), new BigDecimal("-0.50"), 12000000L, "Financeiro"));
        ativos.add(new AtivoBolsaDto("BBDC4", "Bradesco", new BigDecimal("25.60"), new BigDecimal("0.80"), 10500000L, "Financeiro"));
        ativos.add(new AtivoBolsaDto("ABEV3", "Ambev", new BigDecimal("13.25"), new BigDecimal("2.30"), 20000000L, "Bebidas"));
        ativos.add(new AtivoBolsaDto("JBSS3", "Jbs", new BigDecimal("22.10"), new BigDecimal("-1.20"), 3500000L, "Alimentos"));
        ativos.add(new AtivoBolsaDto("GGBR4", "Gerdau", new BigDecimal("18.50"), new BigDecimal("1.10"), 6800000L, "Siderurgia"));
        ativos.add(new AtivoBolsaDto("SUZB3", "Suzano", new BigDecimal("16.75"), new BigDecimal("0.45"), 4200000L, "Papel"));
        ativos.add(new AtivoBolsaDto("RENT3", "Localiza", new BigDecimal("19.90"), new BigDecimal("2.60"), 2500000L, "Aluguel"));
        return ativos;
    }

    public List<AtivoBolsaDto> obterAtivos() {
        return obterTodosAtivos();
    }

    public Page<AtivoBolsaDto> listarAtivos(Pageable pageable, String setor, String busca) {
        List<AtivoBolsaDto> todosAtivos = obterTodosAtivos();

        List<AtivoBolsaDto> filtrados = todosAtivos.stream()
                .filter(a -> setor == null || a.getSetor().equalsIgnoreCase(setor))
                .filter(a -> busca == null || a.getSimbolo().toUpperCase().contains(busca.toUpperCase()) ||
                        a.getNome().toUpperCase().contains(busca.toUpperCase()))
                .collect(Collectors.toList());

        return new PageImpl<>(filtrados, pageable, filtrados.size());
    }

    public AtivoBolsaDto obterAtivoPorId(Long id) {
        List<AtivoBolsaDto> todosAtivos = obterTodosAtivos();

        // Simulação de ID: PETR4 = 1, VALE3 = 2, WEGE3 = 3, etc.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do ativo deve ser maior que zero");
        }

        if (id > todosAtivos.size()) {
            throw new IllegalArgumentException("Ativo não encontrado");
        }

        return todosAtivos.get(id.intValue() - 1);
    }
}
