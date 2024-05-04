package br.gov.sp.fatec.apipixel.core.domain.dto;

import br.gov.sp.fatec.apipixel.core.domain.enumerations.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosRelatorio {

    private String nomeColaborador;
    private String nomeTrilha;
    private String nomeExpertise;
    private Status status;
}
