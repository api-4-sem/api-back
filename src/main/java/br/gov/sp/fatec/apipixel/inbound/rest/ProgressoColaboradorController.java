package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.command.AcompanharProgressoCommand;
import br.gov.sp.fatec.apipixel.core.domain.command.CarregarTrilhaPorColaboradorCommand;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosExpertisesConcluidasDto;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosProgressoDto;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosProgressoTrilhaDto;
import br.gov.sp.fatec.apipixel.core.domain.projection.ProgressoExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaColaboradorProjection;
import br.gov.sp.fatec.apipixel.core.usecase.progresso.AcompanharHabilidadesUC;
import br.gov.sp.fatec.apipixel.core.usecase.progresso.AcompanharProgressoUC;
import br.gov.sp.fatec.apipixel.core.usecase.progresso.CarregarTrilhaPorColaboradorUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/progresso-colaborador")
@RequiredArgsConstructor
public class ProgressoColaboradorController {

    private final CarregarTrilhaPorColaboradorUC carregarTrilhaPorColaboradorUC;

    private final AcompanharProgressoUC acompanharProgressoUC;

    private final AcompanharHabilidadesUC acompanharHabilidadesUC;

    @GetMapping("{colaboradorId}")
    public ResponseEntity<List<TrilhaColaboradorProjection>> carregarTrilhaPorColaborador(
            @PathVariable("colaboradorId") Long colaboradorId){
        CarregarTrilhaPorColaboradorCommand command = new CarregarTrilhaPorColaboradorCommand(colaboradorId);
        return ResponseEntity.ok(carregarTrilhaPorColaboradorUC.executar(command));
    }

    @GetMapping("progresso/{colaboradorId}")
    public ResponseEntity<DadosProgressoDto> carregarDadosProgresso(
            @PathVariable("colaboradorId") Long colaboradorId) {
        AcompanharProgressoCommand command = new AcompanharProgressoCommand(colaboradorId);
        DadosProgressoDto dadosProgressoDto = acompanharProgressoUC.executar(command);
        return ResponseEntity.ok(dadosProgressoDto);
    }

    @GetMapping("habilidades/{colaboradorId}")
    public ResponseEntity<List<DadosExpertisesConcluidasDto>> carregarHabilidades(
            @PathVariable("colaboradorId") Long colaboradorId) {
        AcompanharProgressoCommand command = new AcompanharProgressoCommand(colaboradorId);
        List<DadosExpertisesConcluidasDto> dadosProgressoDto = acompanharHabilidadesUC.executar(command);
        return ResponseEntity.ok(dadosProgressoDto);
    }
}
