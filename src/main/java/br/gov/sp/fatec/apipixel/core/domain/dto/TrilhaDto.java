package br.gov.sp.fatec.apipixel.core.domain.dto;

import br.gov.sp.fatec.apipixel.core.domain.projection.TrilhaProgressoProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrilhaDto {

    private Long id;
    private String nome;
    private Long progressoColaborador;

    public static TrilhaDto toDto(TrilhaProgressoProjection projection){
        return new TrilhaDto(
            projection.getTrilha().getId(), 
            projection.getTrilha().getWorkload(), 
            projection.getProgresso().getPorcentagemAndamento()
        );
    }
}
