package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.command.CarregarDadosExpertiseCommand;
import br.gov.sp.fatec.apipixel.core.domain.command.CarregarTrilhaPorEmpresaCommand;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosExpertisesDto;
import br.gov.sp.fatec.apipixel.core.domain.dto.TrilhaDto;
import br.gov.sp.fatec.apipixel.core.usecase.dashboard.CarregarDadosExpertiseUC;
import br.gov.sp.fatec.apipixel.core.usecase.trilha.CarregarTrilhaPorEmpresa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dash")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DashboardController {

    private final CarregarDadosExpertiseUC carregarDadosExpertiseUC;
    private final CarregarTrilhaPorEmpresa carregarTrilhaPorEmpresa;

    @GetMapping("expertises/empresa/{empresaId}/trilha/{trilhaId}")
    public ResponseEntity<List<DadosExpertisesDto>> carregarColaborador(@PathVariable("empresaId") Long empresaId,
                                                                        @PathVariable("trilhaId") Long trilhaId){
        CarregarDadosExpertiseCommand command = new CarregarDadosExpertiseCommand(trilhaId,empresaId);
        return ResponseEntity.ok(carregarDadosExpertiseUC.executar(command));
    }

    @GetMapping("trilhas/empresa/{empresaId}")
    public ResponseEntity<List<TrilhaDto>> carregarTrilha(@PathVariable("empresaId") Long empresaId){
        CarregarTrilhaPorEmpresaCommand command = new CarregarTrilhaPorEmpresaCommand(empresaId);
        return ResponseEntity.ok(carregarTrilhaPorEmpresa.executar(command));
    }

}
