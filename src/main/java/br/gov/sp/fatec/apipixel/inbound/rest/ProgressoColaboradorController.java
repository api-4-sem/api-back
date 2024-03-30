package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.command.CarregarTrilhaPorColaboradorCommand;
import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaColaboradorProjection;
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

    @GetMapping("{colaboradorId}")
    public ResponseEntity<List<TrilhaColaboradorProjection>> carregarTrilhaPorColaborador(
            @PathVariable("colaboradorId") Long colaboradorId){
        CarregarTrilhaPorColaboradorCommand command = new CarregarTrilhaPorColaboradorCommand(colaboradorId);
        return ResponseEntity.ok(carregarTrilhaPorColaboradorUC.executar(command));
    }
}
