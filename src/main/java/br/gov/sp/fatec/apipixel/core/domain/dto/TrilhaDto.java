package br.gov.sp.fatec.apipixel.core.domain.dto;

import br.gov.sp.fatec.apipixel.core.domain.entity.Trilha;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrilhaDto {

    private Long id;
    private String nome;

    public static TrilhaDto toDto(Trilha trilha){
        return new TrilhaDto(trilha.getId(), trilha.getWorkload());
    }
}
