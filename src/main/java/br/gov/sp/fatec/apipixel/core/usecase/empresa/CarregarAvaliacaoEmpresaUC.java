package br.gov.sp.fatec.apipixel.core.usecase.empresa;

import br.gov.sp.fatec.apipixel.core.domain.projection.AvaliacaoEmpresaProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.AvaliacaoEmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarregarAvaliacaoEmpresaUC {

    private final AvaliacaoEmpresaRepository repo;

    public CarregarAvaliacaoEmpresaUC(AvaliacaoEmpresaRepository repo){
        this.repo = repo;
    }

    public List<AvaliacaoEmpresaProjection> executar(Long id){
        return repo.findByEmpresaId(id);
    }
}
