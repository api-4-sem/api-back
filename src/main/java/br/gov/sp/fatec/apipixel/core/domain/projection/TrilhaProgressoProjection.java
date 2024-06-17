package br.gov.sp.fatec.apipixel.core.domain.projection;

import br.gov.sp.fatec.apipixel.core.domain.entity.ProgressoColaborador;
import br.gov.sp.fatec.apipixel.core.domain.entity.Trilha;

public class TrilhaProgressoProjection {
    private Trilha trilha;
    private ProgressoColaborador progresso;

    public TrilhaProgressoProjection(Trilha trilha, ProgressoColaborador progresso) {
        this.trilha = trilha;
        this.progresso = progresso;
    }

    public Trilha getTrilha() {
        return trilha;
    }

    public ProgressoColaborador getProgresso() {
        return progresso;
    }
}
