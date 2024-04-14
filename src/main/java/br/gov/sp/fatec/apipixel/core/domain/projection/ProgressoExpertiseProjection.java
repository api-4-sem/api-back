package br.gov.sp.fatec.apipixel.core.domain.projection;

import java.time.LocalDate;

public interface ProgressoExpertiseProjection {

    Long getExpertiseId();
    LocalDate getDataFim();
}
