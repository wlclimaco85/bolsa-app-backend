package br.com.abracocontabilidade.bolsa.repository;

import br.com.abracocontabilidade.bolsa.entity.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    Optional<Ativo> findByTicker(String ticker);
}
