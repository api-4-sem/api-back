package br.gov.sp.fatec.apipixel.outbound.jpa;

import br.gov.sp.fatec.apipixel.core.domain.entity.ProgressoColaborador;
import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaColaboradorProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ProgressoColaboradorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressoColaboradorJpaRepository extends JpaRepository<ProgressoColaborador, Long>, ProgressoColaboradorRepository {

    @Query(value = """
                select t.workload as nomeTrilha, pc.porcentagem_andamento as porcentagemAndamento from progresso_colaborador pc
                    join trilha_curso tc on pc.trilha_curso_id = tc.id
                    join trilha t on tc.trilha_id = t.id
                where pc.colaborador_id = :colaboradorId
                group by t.workload, pc.porcentagem_andamento
            """, nativeQuery = true)
    List<TrilhaColaboradorProjection> carregarTrilhaPorColaborador(Long colaboradorId);
}
