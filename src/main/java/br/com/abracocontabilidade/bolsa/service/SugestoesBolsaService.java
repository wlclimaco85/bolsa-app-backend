package br.com.abracocontabilidade.bolsa.service;

import br.com.abracocontabilidade.bolsa.dto.SugestaoAtivoBolsaDto;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SugestoesBolsaService {

    public List<SugestaoAtivoBolsaDto> obterSugestoes() {
        List<SugestaoAtivoBolsaDto> sugestoes = new ArrayList<>();

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "MGLU3",
                "Magazine Luiza",
                new BigDecimal("4.25"),
                "Recuperação de tendência de longo prazo",
                new BigDecimal("78.50")
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "ASAI3",
                "Asaí Atacadista",
                new BigDecimal("14.80"),
                "Crescimento do segmento de varejo",
                new BigDecimal("82.00")
        ));

        sugestoes.add(new SugestaoAtivoBolsaDto(
                "MBLY3",
                "Mobilya",
                new BigDecimal("8.90"),
                "Potencial de expansão doméstica",
                new BigDecimal("65.25")
        ));

        return sugestoes;
    }
}
