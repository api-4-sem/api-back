package br.gov.sp.fatec.apipixel.core.domain.repository;

import java.util.List;

public interface TrilhaCursoRepository {

    List<Long> findExpertisesByTrilhaId(Long trilhaId);
}
