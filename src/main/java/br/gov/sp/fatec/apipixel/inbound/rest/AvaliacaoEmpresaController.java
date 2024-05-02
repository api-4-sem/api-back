package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.projection.AvaliacaoEmpresaProjection;
import br.gov.sp.fatec.apipixel.core.usecase.empresa.CarregarAvaliacaoEmpresaUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/avaliacoes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AvaliacaoEmpresaController {

    private final CarregarAvaliacaoEmpresaUC carregarAvaliacaoEmpresaUC;

    @GetMapping("{empresaId}")
    public ResponseEntity<List<AvaliacaoEmpresaProjection>> carregarColaborador(@PathVariable("empresaId") Long empresaId){
        return ResponseEntity.ok(carregarAvaliacaoEmpresaUC.executar(empresaId));
    }
}
