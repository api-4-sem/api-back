package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaProjection;

import java.util.List;

public interface TrilhaRepository {

    List<TrilhaProjection> carregar();
}
