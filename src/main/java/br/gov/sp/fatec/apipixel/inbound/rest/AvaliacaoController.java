package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.entity.Avaliacao;
import br.gov.sp.fatec.apipixel.core.domain.projection.AvaliacaoProjection;
import br.gov.sp.fatec.apipixel.core.usecase.avaliacao.CarregarAvaliacaoUC;
import br.gov.sp.fatec.apipixel.core.usecase.avaliacao.CriarAvaliacaoUC;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/avaliacoes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AvaliacaoController {

    private final CarregarAvaliacaoUC carregarAvaliacaoEmpresaUC;
    private final CriarAvaliacaoUC criarAvaliacaoUC;

    @GetMapping("{empresaId}")
    public ResponseEntity<List<AvaliacaoProjection>> carregarColaborador(@PathVariable("empresaId") Long empresaId){
        return ResponseEntity.ok(carregarAvaliacaoEmpresaUC.executar(empresaId));
    }

    @PostMapping("/criar-avaliacao")
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao avaliacaoCriada = criarAvaliacaoUC.executar(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoCriada);
    }
}
