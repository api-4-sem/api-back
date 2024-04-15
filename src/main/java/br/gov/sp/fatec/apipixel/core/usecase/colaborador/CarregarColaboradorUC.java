package br.gov.sp.fatec.apipixel.core.usecase.colaborador;

import br.gov.sp.fatec.apipixel.core.domain.command.CarregarColaboradorCommand;
import br.gov.sp.fatec.apipixel.core.domain.projection.ColaboradorProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarregarColaboradorUC {

    private final ColaboradorRepository colaboradorRepository;

    public CarregarColaboradorUC(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<ColaboradorProjection> executar(CarregarColaboradorCommand command){
        return colaboradorRepository.carregar(command.getEmpresaId());
    }
}
