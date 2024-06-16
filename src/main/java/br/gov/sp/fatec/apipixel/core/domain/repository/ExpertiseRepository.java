package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.projection.ExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.domain.projection.StatAvaliacaoPorEmpresa;

import java.util.List;

public interface ExpertiseRepository {

    List<ExpertiseProjection> carregar();
    List<StatAvaliacaoPorEmpresa> findEmpresaIdCountAndAvgNotaByTrilhaIdAndExpertiseId(Long trilhaId, Long expertiseId);
}
