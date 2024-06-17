package br.gov.sp.fatec.apipixel.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DadosExpertisesDto {

    private Map<String, ProgressoExpertiseDto> progresso = new HashMap<>();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProgressoExpertiseDto{
        private Long expertiseId;
        private StatusExpertise status;
    }

    public enum StatusExpertise{
        CONCLUIDO, NAO_CONCLUIDO;
    }
}
