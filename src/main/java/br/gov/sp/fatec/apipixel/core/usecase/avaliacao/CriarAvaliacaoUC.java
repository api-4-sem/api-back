package br.gov.sp.fatec.apipixel.core.usecase.avaliacao;

import org.springframework.stereotype.Service;

import br.gov.sp.fatec.apipixel.core.domain.entity.Avaliacao;
import br.gov.sp.fatec.apipixel.core.domain.repository.AvaliacaoRepository;

@Service
public class CriarAvaliacaoUC {
    private final AvaliacaoRepository repo;

    public CriarAvaliacaoUC(AvaliacaoRepository repo) {
        this.repo = repo;
    }

    public Avaliacao executar(Avaliacao avaliacao) {
        return repo.save(avaliacao);
    }
}
