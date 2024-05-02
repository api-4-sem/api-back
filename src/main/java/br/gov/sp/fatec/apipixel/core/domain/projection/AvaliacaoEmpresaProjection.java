package br.gov.sp.fatec.apipixel.core.domain.projection;

import java.time.LocalDateTime;

public interface AvaliacaoEmpresaProjection {
    public Long getId();
    public Long getEmpresaId();
    public LocalDateTime getDataAvaliacao();
    public Long getNota();
    public String getComentario();
}
