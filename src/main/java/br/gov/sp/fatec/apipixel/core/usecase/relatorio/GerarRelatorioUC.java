package br.gov.sp.fatec.apipixel.core.usecase.relatorio;

import br.gov.sp.fatec.apipixel.core.domain.dto.DadosRelatorio;
import br.gov.sp.fatec.apipixel.core.domain.entity.*;
import br.gov.sp.fatec.apipixel.core.domain.enumerations.Status;
import br.gov.sp.fatec.apipixel.core.domain.projection.ProgressoExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.domain.repository.ProgressoColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.domain.repository.TrilhaCursoRepository;
import br.gov.sp.fatec.apipixel.core.service.RelatorioService;
import jakarta.transaction.Transactional;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GerarRelatorioUC {

    private final RelatorioService relatorioService;

    private final ColaboradorRepository colaboradorRepository;
    private final TrilhaCursoRepository trilhaCursoRepository;
    private final ProgressoColaboradorRepository progressoColaboradorRepository;

    public GerarRelatorioUC(RelatorioService relatorioService, ColaboradorRepository colaboradorRepository, TrilhaCursoRepository trilhaCursoRepository, ProgressoColaboradorRepository progressoColaboradorRepository) {
        this.relatorioService = relatorioService;
        this.colaboradorRepository = colaboradorRepository;
        this.trilhaCursoRepository = trilhaCursoRepository;
        this.progressoColaboradorRepository = progressoColaboradorRepository;
    }

    @Transactional
    public InputStreamResource executar(Long idEmpresa) throws IOException {
        List<Colaborador> colaboradores = colaboradorRepository.carregarTodos()
                .stream()
                .filter(colab -> colab.getEmpresa().getId().equals(idEmpresa))
                .toList();

        List<DadosRelatorio> dadosRelatoriosList = new ArrayList<>();

        for (Colaborador colaborador : colaboradores) {
            List<ProgressoColaborador> progressos = progressoColaboradorRepository.carregar();
            List<ProgressoColaborador> progressoColaborador = progressos.stream()
                    .filter(progresso -> progresso.getColaborador().getId().equals(colaborador.getId()))
                    .toList();
            List<TrilhaCurso> trilhaCursos = progressoColaborador.stream()
                    .map(ProgressoColaborador::getTrilhaCurso)
                    .toList();
            List<Trilha> trilhasDistintas = trilhaCursos.stream()
                    .map(TrilhaCurso::getTrilha)
                    .collect(Collectors.toMap(Trilha::getId, Function.identity(), (trilha1, trilha2) -> trilha1))
                    .values().stream().toList();
            for (Trilha trilha : trilhasDistintas) {
                List<Expertise> expertises = trilhaCursoRepository.carregarExpertisesPorTrilha(trilha.getId())
                        .stream()
                        .map(trilhaCurso -> trilhaCurso.getExpertise()).toList();
                for (Expertise expertise : expertises) {
                    List<ProgressoExpertiseProjection> expertisesConcluidas = progressoColaboradorRepository
                            .findExpertisesByColaboradorId(idEmpresa, trilha.getId());
                    List<Long> expertiseConcluidaId = expertisesConcluidas.stream()
                            .map(ProgressoExpertiseProjection::getExpertiseId).toList();
                    DadosRelatorio dadosRelatorio = new DadosRelatorio();
                    dadosRelatorio.setNomeColaborador(colaborador.getNome());
                    dadosRelatorio.setNomeTrilha(trilha.getWorkload());
                    dadosRelatorio.setNomeExpertise(expertise.getNome());
                    dadosRelatorio.setStatus(expertiseConcluidaId.contains(expertise.getId()) ? Status.CONCLUIDO : Status.NAO_CONCLUIDO);
                    dadosRelatoriosList.add(dadosRelatorio);
                }
            }
        }
        return RelatorioService.dataToExcel(dadosRelatoriosList);
    }
}

