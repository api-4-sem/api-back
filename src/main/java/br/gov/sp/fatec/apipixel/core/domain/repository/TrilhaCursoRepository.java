package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.entity.TrilhaCurso;
import br.gov.sp.fatec.apipixel.core.domain.projection.ExpertisesPorTrilhaProjection;

import java.util.List;

public interface TrilhaCursoRepository {

    List<Long> findExpertisesByTrilhaId(Long trilhaId);

    List<ExpertisesPorTrilhaProjection> findExpertisesByTrilha(Long id);

    List<TrilhaCurso> carregarExpertisesPorTrilha(Long trilhaId);
}
