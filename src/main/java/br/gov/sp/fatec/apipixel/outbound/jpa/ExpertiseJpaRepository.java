package br.gov.sp.fatec.apipixel.outbound.jpa;

import br.gov.sp.fatec.apipixel.core.domain.entity.Trilha;
import br.gov.sp.fatec.apipixel.core.domain.projection.ExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ExpertiseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertiseJpaRepository extends JpaRepository<Trilha, Long>, ExpertiseRepository {

    @Query(value = """
                    select id as id, nome as nome from Expertise""")
    List<ExpertiseProjection> carregar();
}
