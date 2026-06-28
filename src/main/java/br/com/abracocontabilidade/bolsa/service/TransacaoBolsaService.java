package br.com.abracocontabilidade.bolsa.service;

import br.com.abracocontabilidade.bolsa.dto.TransacaoBolsaDto;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransacaoBolsaService {

    private static final AtomicLong idGerador = new AtomicLong(1);

    public TransacaoBolsaDto criarTransacao(TransacaoBolsaDto transacao) {
        // Gera ID em memória
        transacao.setId(idGerador.getAndIncrement());
        return transacao;
    }
}
