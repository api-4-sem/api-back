package br.gov.sp.fatec.apipixel.outbound.jpa;

import br.gov.sp.fatec.apipixel.core.domain.entity.Colaborador;
import br.gov.sp.fatec.apipixel.core.domain.entity.ProgressoColaborador;
import br.gov.sp.fatec.apipixel.core.domain.projection.ColaboradorOciosoProjection;
import br.gov.sp.fatec.apipixel.core.domain.projection.ProgressoExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaColaboradorProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ProgressoColaboradorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    default List<ProgressoColaborador> carregar(){
        return findAll();
    }

    @Query(value = """
                SELECT e.id AS expertiseId, pc.data_fim as dataFim, e.nome as nome FROM progresso_colaborador pc
                    JOIN trilha_curso tc ON pc.trilha_curso_id = tc.id
                    JOIN expertise e ON tc.expertise_id = e.id
                    JOIN trilha t ON tc.trilha_id = t.id
                WHERE pc.colaborador_id = :colaboradorId
                AND t.id = :trilhaId
                AND pc.data_fim is not null
            """, nativeQuery = true)
    List<ProgressoExpertiseProjection> findExpertisesByColaboradorId(@Param("colaboradorId") Long colaboradorId, @Param("trilhaId") Long trilhaId);
    @Query(value = """
            select
                pc1_0.colaborador_id as colaboradorId
            from
                progresso_colaborador pc1_0
            where
                pc1_0.data_fim is null
                and SYSDATE >= pc1_0.DATA_INICIO + INTERVAL '7' DAY
            """, nativeQuery = true)
    List<ColaboradorOciosoProjection> carregarProgressoOcioso();
}
