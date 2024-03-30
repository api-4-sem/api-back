package br.gov.sp.fatec.apipixel.outbound.jpa;

import br.gov.sp.fatec.apipixel.core.domain.dto.ColaboradorDto;
import br.gov.sp.fatec.apipixel.core.domain.entity.Colaborador;
import br.gov.sp.fatec.apipixel.core.domain.repository.ColaboradorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColaboradorJpaRepository extends JpaRepository<Colaborador, Long>, ColaboradorRepository {

    @Query(value = """
                    select nome from Colaborador where empresa.id = :empresaId""")
    List<String> carregar(Long empresaId);

}
