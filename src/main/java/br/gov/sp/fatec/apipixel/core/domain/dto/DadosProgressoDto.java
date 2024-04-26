package br.gov.sp.fatec.apipixel.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DadosProgressoDto {

    private String nomeColaborador;
    private String nomeEmpresa;
    private List<DadosProgressoTrilhaDto> dadosTrilha;
}

