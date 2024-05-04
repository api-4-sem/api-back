package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.entity.Empresa;
import br.gov.sp.fatec.apipixel.core.domain.projection.EmpresaProjection;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository {

    Optional<Empresa> carregarEmpresa(Long empresaId);
    List<EmpresaProjection> carregar();
    Empresa save(Empresa empresa);

}
