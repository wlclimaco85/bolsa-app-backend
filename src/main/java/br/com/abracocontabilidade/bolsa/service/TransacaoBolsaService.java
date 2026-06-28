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
        // Validar usuarioId
        if (transacao.getUsuarioId() == null) {
            throw new IllegalArgumentException("Usuário ID é obrigatório");
        }

        if (transacao.getUsuarioId() <= 0) {
            throw new IllegalArgumentException("Usuário ID deve ser maior que zero");
        }

        // Validar tipo
        if (transacao.getTipo() == null || (!transacao.getTipo().equalsIgnoreCase("COMPRA") && !transacao.getTipo().equalsIgnoreCase("VENDA"))) {
            throw new IllegalArgumentException("Tipo de transação deve ser COMPRA ou VENDA");
        }

        // Validar quantidade
        if (transacao.getQuantidade() == null || transacao.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        // Validar preço
        if (transacao.getPreco() == null || transacao.getPreco().signum() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }

        // Validar saldo para VENDA
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
