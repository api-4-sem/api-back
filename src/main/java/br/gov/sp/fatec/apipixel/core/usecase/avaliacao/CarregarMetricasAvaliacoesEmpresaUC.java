package br.gov.sp.fatec.apipixel.core.usecase.avaliacao;

import br.gov.sp.fatec.apipixel.core.domain.entity.Avaliacao;
import br.gov.sp.fatec.apipixel.core.domain.entity.MetricasAvaliacoesEmpresa;
import br.gov.sp.fatec.apipixel.core.domain.projection.AvaliacaoProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.AvaliacaoRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CarregarMetricasAvaliacoesEmpresaUC {
    private final AvaliacaoRepository repo;

    public CarregarMetricasAvaliacoesEmpresaUC(AvaliacaoRepository repo){
        this.repo = repo;
    }

    public MetricasAvaliacoesEmpresa executar(Long empresaId){
        List<AvaliacaoProjection> avaliacoes = repo.findByEmpresaId(empresaId);
        int numeroAvaliacoes = avaliacoes.size();
        Double mediaNotas = avaliacoes.stream().mapToLong(AvaliacaoProjection::getNota).average().orElse(0.0);

        return new MetricasAvaliacoesEmpresa(empresaId, mediaNotas, numeroAvaliacoes);
    }
}
