package br.gov.sp.fatec.apipixel.core.usecase.avaliacao;

import br.gov.sp.fatec.apipixel.core.domain.projection.AvaliacaoProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarregarAvaliacaoUC {

    private final AvaliacaoRepository repo;

    public CarregarAvaliacaoUC(AvaliacaoRepository repo){
        this.repo = repo;
    }

    public List<AvaliacaoProjection> executar(Long id){
        return repo.findByEmpresaId(id);
    }
}
