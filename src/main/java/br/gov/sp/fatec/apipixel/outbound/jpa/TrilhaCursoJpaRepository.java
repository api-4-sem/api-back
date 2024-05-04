package br.gov.sp.fatec.apipixel.outbound.jpa;

import br.gov.sp.fatec.apipixel.core.domain.entity.TrilhaCurso;
import br.gov.sp.fatec.apipixel.core.domain.projection.ExpertisesPorTrilhaProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.TrilhaCursoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrilhaCursoJpaRepository extends JpaRepository<TrilhaCurso,Long>, TrilhaCursoRepository {
    @Query(value = """
                select e.id as expertiseId from trilha_curso tc
                    join expertise e on tc.expertise_id = e.id
                where tc.trilha_id = :trilhaId
            """, nativeQuery = true)
    List<Long> findExpertisesByTrilhaId(@Param("trilhaId") Long trilhaId);

    @Query(value = """
                select e.id as id, e.nome as nome from trilha_curso tc
                    join expertise e on tc.expertise_id = e.id
                where tc.trilha_id = :trilhaId
            """, nativeQuery = true)
    List<ExpertisesPorTrilhaProjection> findExpertisesByTrilha(@Param("trilhaId") Long trilhaId);

    @Override
    default List<TrilhaCurso> carregarExpertisesPorTrilha(Long trilhaId) {
        return findByTrilhaId(trilhaId);
    }

    List<TrilhaCurso> findByTrilhaId(Long trilhaId);
}
