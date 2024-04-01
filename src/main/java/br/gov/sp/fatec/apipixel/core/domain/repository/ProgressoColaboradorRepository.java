package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaColaboradorProjection;

import java.util.List;

public interface ProgressoColaboradorRepository {

    List<TrilhaColaboradorProjection> carregarTrilhaPorColaborador(Long colaboradorId);
}
