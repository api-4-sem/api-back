package br.gov.sp.fatec.apipixel.outbound.jpa;

import br.gov.sp.fatec.apipixel.core.domain.entity.Empresa;
import br.gov.sp.fatec.apipixel.core.domain.projection.EmpresaProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.EmpresaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface EmpresaJpaRepository extends JpaRepository<Empresa, Long>, EmpresaRepository {

    @Query(value = """
                    select e.id as id, e.codigo as codigo, e.nome as nome, e.cidade as cidade, e.pais as pais, 
                    e.adminNome as adminNome, e.adminEmail as adminEmail 
                    from Empresa e""")
    List<EmpresaProjection> carregar();

    default Optional<Empresa> carregarEmpresa(Long empresaId) {
        return findById(empresaId);
    }
}
