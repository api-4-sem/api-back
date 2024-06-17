package br.gov.sp.fatec.apipixel.core.domain.projection;

public interface EmpresaProjection {
    Long getId();
    String getNome();
    String getAdminNome();
    String getAdminEmail();
    String getPais();
    String getCidade();
    String getEstado();
    Long getCodigo();
}
