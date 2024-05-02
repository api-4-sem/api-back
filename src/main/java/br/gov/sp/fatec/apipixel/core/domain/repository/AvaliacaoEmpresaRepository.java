package br.gov.sp.fatec.apipixel.core.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.apipixel.core.domain.entity.AvaliacaoEmpresa;
import br.gov.sp.fatec.apipixel.core.domain.projection.AvaliacaoEmpresaProjection;

public interface AvaliacaoEmpresaRepository extends JpaRepository<AvaliacaoEmpresa, Long>{
    List<AvaliacaoEmpresaProjection> findByEmpresaId(Long empresaId);
}
