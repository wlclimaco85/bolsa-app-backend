package br.com.abracocontabilidade.bolsa.service;

import br.com.abracocontabilidade.bolsa.dto.TransacaoBolsaDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransacaoBolsaService {

    private static final AtomicLong idGerador = new AtomicLong(1);
    private final List<TransacaoBolsaDto> transacoes = new ArrayList<>();

    public TransacaoBolsaDto criarTransacao(TransacaoBolsaDto transacao) {
        validarTransacao(transacao);

        transacao.setId(idGerador.getAndIncrement());
        transacoes.add(transacao);
        return transacao;
    }

    private void validarTransacao(TransacaoBolsaDto transacao) {
        if (transacao.getQuantidade() == null || transacao.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (transacao.getPreco() == null || transacao.getPreco().signum() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }

        if ("VENDA".equalsIgnoreCase(transacao.getTipo())) {
            validarSaldoVenda(transacao.getSimbolo(), transacao.getQuantidade());
        }
    }

    private void validarSaldoVenda(String simbolo, Integer quantidade) {
        long saldoCompra = transacoes.stream()
                .filter(t -> simbolo.equals(t.getSimbolo()) && "COMPRA".equalsIgnoreCase(t.getTipo()))
                .mapToLong(TransacaoBolsaDto::getQuantidade)
                .sum();

        long saldoVenda = transacoes.stream()
                .filter(t -> simbolo.equals(t.getSimbolo()) && "VENDA".equalsIgnoreCase(t.getTipo()))
                .mapToLong(TransacaoBolsaDto::getQuantidade)
                .sum();

        long saldoDisponivel = saldoCompra - saldoVenda;

        if (saldoDisponivel < quantidade) {
            throw new IllegalArgumentException("Saldo insuficiente para VENDA");
        }
    }
}
