package br.gov.sp.fatec.apipixel.core.usecase.trilha;

import br.gov.sp.fatec.apipixel.core.domain.command.CarregarTrilhaPorEmpresaCommand;
import br.gov.sp.fatec.apipixel.core.domain.dto.TrilhaDto;
import br.gov.sp.fatec.apipixel.core.domain.entity.ProgressoColaborador;
import br.gov.sp.fatec.apipixel.core.domain.entity.Trilha;
import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaProgressoProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ProgressoColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarregarTrilhaPorEmpresa {

    private final ProgressoColaboradorRepository progressoColaboradorRepository;

    public CarregarTrilhaPorEmpresa(ProgressoColaboradorRepository progressoColaboradorRepository) {
        this.progressoColaboradorRepository = progressoColaboradorRepository;
    }

    public List<TrilhaDto> executar(CarregarTrilhaPorEmpresaCommand command) {
        var trilhas = progressoColaboradorRepository.carregar().stream()
                .filter(progresso -> progresso.getColaborador().getEmpresa().getId().equals(command.getId()))
                .map(progresso -> new TrilhaProgressoProjection(progresso.getTrilhaCurso().getTrilha(), progresso))
                .collect(Collectors.toSet());

        return trilhas.stream()
                .map(TrilhaDto::toDto).toList();
    }
}
