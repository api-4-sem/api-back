package br.gov.sp.fatec.apipixel.core.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarregarTrilhaPorColaboradorCommand {

    private Long colaboradorId;
}
