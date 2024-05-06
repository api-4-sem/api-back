package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.entity.ProgressoColaborador;
import br.gov.sp.fatec.apipixel.core.domain.projection.ProgressoExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaColaboradorProjection;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProgressoColaboradorRepository {

    List<TrilhaColaboradorProjection> carregarTrilhaPorColaborador(Long colaboradorId);

    List<ProgressoColaborador> carregar();

    List<ProgressoExpertiseProjection> findExpertisesByColaboradorId(Long colaboradorId, Long trilhaId);

    List<ProgressoColaborador> carregarProgressoOcioso(LocalDateTime data);
}
