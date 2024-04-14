package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.entity.Colaborador;
import br.gov.sp.fatec.apipixel.core.domain.projection.ColaboradorProjection;

import java.util.List;
import java.util.Optional;

public interface ColaboradorRepository {

    List<ColaboradorProjection> carregar(Long empresaId);

    Colaborador findColaboradorById(Long id);

    Optional<Colaborador> carregarColaborador(Long colaboradorId);
}
