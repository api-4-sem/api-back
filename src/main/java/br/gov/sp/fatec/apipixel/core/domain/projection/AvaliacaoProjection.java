package br.gov.sp.fatec.apipixel.core.domain.projection;

import java.time.LocalDateTime;

public interface AvaliacaoProjection {
    public Long getId();
    public Long getEmpresaId();
    public Long getColaboradorId();
    public Long getTrilhaId();
    public Long getExpertiseId();
    public LocalDateTime getDataAvaliacao();
    public String getFeedback();
    public Long getNota();
}
