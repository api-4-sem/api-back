package br.gov.sp.fatec.apipixel.core.domain.projection;

public class StatAvaliacaoPorEmpresa {

    private Long empresaId;
    private String empresaNome;
    private Long count;
    private Double avgNota;

    public StatAvaliacaoPorEmpresa(Long empresaId, String empresaNome, Long count, Double avgNota) {
        this.empresaId = empresaId;
        this.empresaNome = empresaNome;
        this.count = count;
        this.avgNota = avgNota;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getAvgNota() {
        return avgNota;
    }

    public void setAvgNota(Double avgNota) {
        this.avgNota = avgNota;
    }
}
