package br.com.abracocontabilidade.bolsa.entity;

import br.com.abracocontabilidade.bolsa.mapper.AtivoMapper;
import br.com.abracocontabilidade.bolsa.dto.AtivoDto;
import br.com.abracocontabilidade.bolsa.repository.AtivoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(AtivoMapper.class)
class AtivoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private AtivoMapper ativoMapper;

    /**
     * RED: Teste 1 - Deve salvar Ativo com todos os campos
     */
    @Test
    void deveSalvarAtivoComTodosCampos() {
        Ativo ativo = Ativo.builder()
                .ticker("PETR4")
                .nome("Petrobras")
                .setor("Energia")
                .precoAtual(new BigDecimal("35.50"))
                .dataAtualizacao(LocalDateTime.now())
                .build();

        Ativo salvo = ativoRepository.save(ativo);
        entityManager.flush();

        assertNotNull(salvo.getId());
        assertEquals("PETR4", salvo.getTicker());
        assertEquals("Petrobras", salvo.getNome());
        assertEquals("Energia", salvo.getSetor());
        assertEquals(new BigDecimal("35.50"), salvo.getPrecoAtual());
        assertNotNull(salvo.getDataAtualizacao());
    }

    /**
     * RED: Teste 2 - Deve mapear Ativo para AtivoDto corretamente
     */
    @Test
    void deveMapearAtivoParaAtivoDto() {
        Ativo ativo = Ativo.builder()
                .id(1L)
                .ticker("VALE3")
                .nome("Vale")
                .setor("Mineração")
                .precoAtual(new BigDecimal("65.75"))
                .dataAtualizacao(LocalDateTime.now())
                .build();

        AtivoDto dto = ativoMapper.paraDto(ativo);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("VALE3", dto.getTicker());
        assertEquals("Vale", dto.getNome());
        assertEquals("Mineração", dto.getSetor());
        assertEquals(new BigDecimal("65.75"), dto.getPrecoAtual());
        assertNotNull(dto.getDataAtualizacao());
    }
}
