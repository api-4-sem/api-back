package br.gov.sp.fatec.apipixel.core.usecase.colaborador;

import br.gov.sp.fatec.apipixel.core.domain.command.CarregarColaboradorCommand;
import br.gov.sp.fatec.apipixel.core.domain.dto.ColaboradorDto;
import br.gov.sp.fatec.apipixel.core.domain.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarregarColaboradorUC {

    private final ColaboradorRepository colaboradorRepository;

    public CarregarColaboradorUC(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<ColaboradorDto> executar(CarregarColaboradorCommand command){
        List<String> colaborades = colaboradorRepository.carregar(command.getEmpresaId());
        return colaborades.stream().map(ColaboradorDto::new).collect(Collectors.toList());
    }
}
