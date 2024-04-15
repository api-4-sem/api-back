package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.projection.ExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.usecase.expertise.CarregarExpertiseUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/carregar-expertises")
@RequiredArgsConstructor
public class ExpertiseController {

    private final CarregarExpertiseUC carregarExpertiseUC;

    @GetMapping
    public ResponseEntity<List<ExpertiseProjection>> findAll(){
        return ResponseEntity.ok(carregarExpertiseUC.executar());
    }
}
