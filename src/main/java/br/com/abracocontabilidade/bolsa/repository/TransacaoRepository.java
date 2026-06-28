package br.com.abracocontabilidade.bolsa.repository;

import br.com.abracocontabilidade.bolsa.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByUsuarioId(Long usuarioId);
    List<Transacao> findByAtivoId(Long ativoId);
    List<Transacao> findByUsuarioIdAndAtivoId(Long usuarioId, Long ativoId);
}
