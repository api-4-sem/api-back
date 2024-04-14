package br.gov.sp.fatec.apipixel.core.usecase.trilha;

import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.TrilhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarregarTrilhaUC {

    private final TrilhaRepository trilhaRepository;

    public CarregarTrilhaUC(TrilhaRepository trilhaRepository){
        this.trilhaRepository = trilhaRepository;
    }

    public List<TrilhaProjection> executar(){
        return trilhaRepository.carregar();
    }
}
