package br.gov.sp.fatec.apipixel.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetricasAvaliacoesEmpresa {
    private Long empresaId;
    private Double mediaNotas;
    private int numeroAvaliacoes;
}
