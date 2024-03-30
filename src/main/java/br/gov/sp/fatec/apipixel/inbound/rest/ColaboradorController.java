package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.command.CarregarColaboradorCommand;
import br.gov.sp.fatec.apipixel.core.domain.dto.ColaboradorDto;
import br.gov.sp.fatec.apipixel.core.usecase.colaborador.CarregarColaboradorUC;
import jakarta.persistence.Entity;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/colaborador")
@RequiredArgsConstructor
public class ColaboradorController {

    private final CarregarColaboradorUC carregarColaboradorUC;

    @GetMapping("{empresaId}")
    public ResponseEntity<List<ColaboradorDto>> carregarColaborador(@PathVariable("empresaId") Long empresaId){
        CarregarColaboradorCommand command = new CarregarColaboradorCommand(empresaId);
        return ResponseEntity.ok(carregarColaboradorUC.executar(command));
    }
}
