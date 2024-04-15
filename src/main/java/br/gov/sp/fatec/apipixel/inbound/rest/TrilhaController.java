package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaProjection;
import br.gov.sp.fatec.apipixel.core.usecase.trilha.CarregarTrilhaUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/carregar-trilhas")
@RequiredArgsConstructor
public class TrilhaController {

    private final CarregarTrilhaUC carregarTrilhaUC;

    @GetMapping
    public ResponseEntity<List<TrilhaProjection>> findAll(){
        return ResponseEntity.ok(carregarTrilhaUC.executar());
    }
}
