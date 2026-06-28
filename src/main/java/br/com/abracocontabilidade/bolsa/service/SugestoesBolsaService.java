package br.com.abracocontabilidade.bolsa.service;

import br.com.abracocontabilidade.bolsa.dto.SugestaoAtivoBolsaDto;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SugestoesBolsaService {

    public List<SugestaoAtivoBolsaDto> obterSugestoes(int limite) {
        List<SugestaoAtivoBolsaDto> sugestoes = new ArrayList<>();

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "MGLU3",
                "Magazine Luiza",
                new BigDecimal("4.25"),
                "Recuperação de tendência de longo prazo",
                new BigDecimal("78.50"),
                "Varejo"
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "ASAI3",
                "Asaí Atacadista",
                new BigDecimal("14.80"),
                "Crescimento do segmento de varejo",
                new BigDecimal("82.00"),
                "Varejo"
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "MBLY3",
                "Mobilya",
                new BigDecimal("8.90"),
                "Potencial de expansão doméstica",
                new BigDecimal("65.25"),
                "Varejo"
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "VALE3",
                "Vale S.A.",
                new BigDecimal("58.40"),
                "Trending em commodities",
                new BigDecimal("85.00"),
                "Mineração"
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "PETR4",
                "Petrobras",
                new BigDecimal("32.10"),
                "Faltam no portfolio - dividendos",
                new BigDecimal("76.75"),
                "Energia"
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "WEGE3",
                "WEG S.A.",
                new BigDecimal("97.80"),
                "Diversificação - engenharia",
                new BigDecimal("88.50"),
                "Indústria"
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "ITUB4",
                "Itaú Unibanco",
                new BigDecimal("27.50"),
                "Faltam no portfolio - setor financeiro",
                new BigDecimal("79.25"),
                "Financeiro"
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "B3SA3",
                "B3 S.A.",
                new BigDecimal("12.65"),
                "Trending - infraestrutura",
                new BigDecimal("81.00"),
                "Financeiro"
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "LREN3",
                "Lojas Renner",
                new BigDecimal("21.90"),
                "Recuperação pós-queda",
                new BigDecimal("72.40"),
                "Varejo"
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "BBAS3",
                "Banco do Brasil",
                new BigDecimal("41.35"),
                "Faltam no portfolio - financeiro estatal",
                new BigDecimal("74.80"),
                "Financeiro"
        ));

        return sugestoes.stream()
                .limit(limite)
                .collect(Collectors.toList());
    }
}
