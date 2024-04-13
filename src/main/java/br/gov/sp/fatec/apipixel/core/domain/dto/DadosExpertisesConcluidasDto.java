package br.gov.sp.fatec.apipixel.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DadosExpertisesConcluidasDto {

    private String nomeTrilha;
    private Map<String, Object> expertisesPorTrilha;
    private Map<String, Object> expertisesConcluidas;
}
