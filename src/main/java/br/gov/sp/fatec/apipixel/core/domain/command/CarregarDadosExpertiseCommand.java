package br.gov.sp.fatec.apipixel.core.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarregarDadosExpertiseCommand {
    private Long trilhaId;
    private Long empresaId;
}
