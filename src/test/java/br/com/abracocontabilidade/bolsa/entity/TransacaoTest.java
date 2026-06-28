package br.com.abracocontabilidade.bolsa.entity;

import br.com.abracocontabilidade.bolsa.mapper.TransacaoMapper;
import br.com.abracocontabilidade.bolsa.dto.TransacaoDto;
import br.com.abracocontabilidade.bolsa.repository.TransacaoRepository;
import br.com.abracocontabilidade.bolsa.repository.AtivoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({TransacaoMapper.class})
class TransacaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private TransacaoMapper transacaoMapper;

    private Ativo ativoSalvo;

    @BeforeEach
    void setUp() {
        Ativo ativo = Ativo.builder()
                .ticker("PETR4")
                .nome("Petrobras")
                .setor("Energia")
                .precoAtual(new BigDecimal("35.50"))
                .dataAtualizacao(LocalDateTime.now())
                .build();
        ativoSalvo = ativoRepository.save(ativo);
        entityManager.flush();
    }

    /**
     * RED: Teste 1 - Deve salvar Transacao com todos os campos
     */
    @Test
    void deveSalvarTransacaoComValidacoes() {
        Transacao transacao = Transacao.builder()
                .usuarioId(1L)
                .ativo(ativoSalvo)
                .quantidade(10)
                .preco(new BigDecimal("35.50"))
                .dataHora(LocalDateTime.now())
                .corretora("BTG Pactual")
                .tipo(TipoTransacao.COMPRA)
                .build();

        Transacao salva = transacaoRepository.save(transacao);
        entityManager.flush();

        assertNotNull(salva.getId());
        assertEquals(1L, salva.getUsuarioId());
        assertEquals(ativoSalvo.getId(), salva.getAtivo().getId());
        assertEquals(10, salva.getQuantidade());
        assertEquals(new BigDecimal("35.50"), salva.getPreco());
        assertEquals(TipoTransacao.COMPRA, salva.getTipo());
        assertEquals("BTG Pactual", salva.getCorretora());
    }

    /**
     * RED: Teste 2 - Deve mapear Transacao para TransacaoDto corretamente
     */
    @Test
    void deveMapearTransacaoParaTransacaoDto() {
        Transacao transacao = Transacao.builder()
                .id(1L)
                .usuarioId(1L)
                .ativo(ativoSalvo)
                .quantidade(5)
                .preco(new BigDecimal("65.75"))
                .dataHora(LocalDateTime.now())
                .corretora("Itau")
                .tipo(TipoTransacao.VENDA)
                .build();

        TransacaoDto dto = transacaoMapper.paraDto(transacao);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getUsuarioId());
        assertEquals(ativoSalvo.getId(), dto.getAtivoId());
        assertEquals(5, dto.getQuantidade());
        assertEquals(new BigDecimal("65.75"), dto.getPreco());
        assertEquals(TipoTransacao.VENDA, dto.getTipo());
        assertEquals("Itau", dto.getCorretora());
    }
}
