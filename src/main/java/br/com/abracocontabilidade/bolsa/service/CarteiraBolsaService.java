package br.com.abracocontabilidade.bolsa.service;

import br.com.abracocontabilidade.bolsa.dto.HoldingCarteiraBolsaDto;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarteiraBolsaService {

    public List<HoldingCarteiraBolsaDto> obterCarteira() {
        List<HoldingCarteiraBolsaDto> carteira = new ArrayList<>();

        carteira.add(new HoldingCarteiraBolsaDto(
                "PETR4",
                100,
                new BigDecimal("28.50"),
                new BigDecimal("30.25"),
                new BigDecimal("175.00"),
                new BigDecimal("6.14")
        ));

        carteira.add(new HoldingCarteiraBolsaDto(
                "VALE3",
                50,
                new BigDecimal("60.00"),
                new BigDecimal("62.15"),
                new BigDecimal("107.50"),
                new BigDecimal("3.58")
        ));

        carteira.add(new HoldingCarteiraBolsaDto(
                "WEGE3",
                200,
                new BigDecimal("35.20"),
                new BigDecimal("36.90"),
                new BigDecimal("340.00"),
                new BigDecimal("4.83")
        ));

        return carteira;
    }
}
