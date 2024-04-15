package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.projection.ExpertiseProjection;

import java.util.List;

public interface ExpertiseRepository {

    List<ExpertiseProjection> carregar();
}
