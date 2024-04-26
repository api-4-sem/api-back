package br.gov.sp.fatec.apipixel.core.usecase.expertise;

import br.gov.sp.fatec.apipixel.core.domain.projection.ExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ExpertiseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarregarExpertiseUC {

    private final ExpertiseRepository expertiseRepository;

    public CarregarExpertiseUC(ExpertiseRepository expertiseRepository) {
        this.expertiseRepository = expertiseRepository;
    }

    public List<ExpertiseProjection> executar(){
        return expertiseRepository.carregar();
    }
}
