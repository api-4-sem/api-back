package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.projection.ColaboradorProjection;

import java.util.List;

public interface ColaboradorRepository {

    List<ColaboradorProjection> carregar(Long empresaId);
}
