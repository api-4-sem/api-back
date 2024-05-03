package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.projection.AvaliacaoProjection;
import br.gov.sp.fatec.apipixel.core.usecase.avaliacao.CarregarAvaliacaoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/avaliacoes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AvaliacaoController {

    private final CarregarAvaliacaoUC carregarAvaliacaoEmpresaUC;

    @GetMapping("{empresaId}")
    public ResponseEntity<List<AvaliacaoProjection>> carregarColaborador(@PathVariable("empresaId") Long empresaId){
        return ResponseEntity.ok(carregarAvaliacaoEmpresaUC.executar(empresaId));
    }
}
