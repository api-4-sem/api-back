package br.gov.sp.fatec.apipixel.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DadosProgressoTrilhaDto {

    private LocalDate dataConclusaoTrilha;
    private Long validadeTrilha;
    private String nomeTrilha;
}
