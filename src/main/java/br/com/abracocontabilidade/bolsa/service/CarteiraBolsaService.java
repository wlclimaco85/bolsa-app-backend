package br.com.abracocontabilidade.bolsa.service;

import br.com.abracocontabilidade.bolsa.dto.CarteiraBolsaDto;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarteiraBolsaService {

    public List<CarteiraBolsaDto> obterCarteira() {
        List<CarteiraBolsaDto> carteira = new ArrayList<>();

        carteira.add(new CarteiraBolsaDto(
                "PETR4",
                "Petróbras",
                100,
                new BigDecimal("28.50"),
                new BigDecimal("30.25"),
                new BigDecimal("6.14")
        ));

        carteira.add(new CarteiraBolsaDto(
                "VALE3",
                "Vale",
                50,
                new BigDecimal("60.00"),
                new BigDecimal("62.15"),
                new BigDecimal("3.58")
        ));

        carteira.add(new CarteiraBolsaDto(
                "WEGE3",
                "Weg",
                200,
                new BigDecimal("35.20"),
                new BigDecimal("36.90"),
                new BigDecimal("4.83")
        ));

        return carteira;
    }
}
