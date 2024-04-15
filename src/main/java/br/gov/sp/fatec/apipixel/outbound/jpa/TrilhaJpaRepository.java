package br.gov.sp.fatec.apipixel.outbound.jpa;

import br.gov.sp.fatec.apipixel.core.domain.entity.Trilha;
import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.TrilhaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrilhaJpaRepository extends JpaRepository<Trilha, Long>, TrilhaRepository {

    @Query(value = """
                    select id as id, workload as workload from Trilha """)
    List<TrilhaProjection> carregar();
}
