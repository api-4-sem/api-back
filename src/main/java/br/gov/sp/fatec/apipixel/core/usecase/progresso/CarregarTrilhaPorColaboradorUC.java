package br.gov.sp.fatec.apipixel.core.usecase.progresso;

import br.gov.sp.fatec.apipixel.core.domain.command.CarregarTrilhaPorColaboradorCommand;
import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaColaboradorProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ProgressoColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarregarTrilhaPorColaboradorUC {

    private final ProgressoColaboradorRepository progressoColaboradorRepository;

    public CarregarTrilhaPorColaboradorUC(ProgressoColaboradorRepository progressoColaboradorRepository) {
        this.progressoColaboradorRepository = progressoColaboradorRepository;
    }

    public List<TrilhaColaboradorProjection> executar(CarregarTrilhaPorColaboradorCommand command){
        return progressoColaboradorRepository.carregarTrilhaPorColaborador(command.getColaboradorId());
    }
}
