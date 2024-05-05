package br.gov.sp.fatec.apipixel.core.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.apipixel.core.domain.entity.Avaliacao;
import br.gov.sp.fatec.apipixel.core.domain.projection.AvaliacaoProjection;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
    List<AvaliacaoProjection> findByEmpresaId(Long empresaId);
}
