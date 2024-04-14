package br.gov.sp.fatec.apipixel.core.usecase.progresso;

import br.gov.sp.fatec.apipixel.core.domain.command.AcompanharProgressoCommand;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosExpertisesConcluidasDto;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosProgressoDto;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosProgressoTrilhaDto;
import br.gov.sp.fatec.apipixel.core.domain.entity.ProgressoColaborador;
import br.gov.sp.fatec.apipixel.core.domain.entity.Trilha;
import br.gov.sp.fatec.apipixel.core.domain.entity.TrilhaCurso;
import br.gov.sp.fatec.apipixel.core.domain.projection.ExpertisesPorTrilhaProjection;
import br.gov.sp.fatec.apipixel.core.domain.projection.ProgressoExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.domain.repository.ProgressoColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.domain.repository.TrilhaCursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AcompanharHabilidadesUC {

    private final ProgressoColaboradorRepository progressoColaboradorRepository;

    private final TrilhaCursoRepository trilhaCurosRepository;

    private final ColaboradorRepository colaboradorRepository;

    public AcompanharHabilidadesUC(ProgressoColaboradorRepository progressoColaboradorRepository, TrilhaCursoRepository trilhaCurosRepository, ColaboradorRepository colaboradorRepository) {
        this.progressoColaboradorRepository = progressoColaboradorRepository;
        this.trilhaCurosRepository = trilhaCurosRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Transactional
    public List<DadosExpertisesConcluidasDto> executar(AcompanharProgressoCommand command){
        List<ProgressoColaborador> progressos = progressoColaboradorRepository.carregar();
        List<ProgressoColaborador> progressoColaborador = progressos.stream()
                .filter(progresso -> progresso.getColaborador().getId().equals(command.getColaboradorId()))
                .collect(Collectors.toList());

        List<DadosExpertisesConcluidasDto> dadosExpertisesConcluidasDtos = obtertDataConclusaoTrilha(progressos,
                command.getColaboradorId());

        return dadosExpertisesConcluidasDtos;
    }

    public List<DadosExpertisesConcluidasDto> obtertDataConclusaoTrilha(List<ProgressoColaborador> progressoColaborador, Long colaboradorId){
        List<TrilhaCurso> trilhaCursos = progressoColaborador.stream()
                .map(ProgressoColaborador::getTrilhaCurso)
                .toList();

        List<Trilha> trilhasDistintas = trilhaCursos.stream()
                .map(TrilhaCurso::getTrilha)
                .collect(Collectors.toMap(Trilha::getId,  Function.identity(), (trilha1, trilha2) -> trilha1))
                .values().stream().toList();

        List<DadosExpertisesConcluidasDto> progressoTrilha = new ArrayList<>();

        for(Trilha trilha : trilhasDistintas){
            DadosExpertisesConcluidasDto dadosExpertisesConcluidasDto = new DadosExpertisesConcluidasDto();
            Map<String, Object> expertisesPorTrilha = new HashMap<>();
            Map<String, Object> expertisesConcluidasMap = new HashMap<>();
            List<ExpertisesPorTrilhaProjection> expertisePorTrilha = trilhaCurosRepository.findExpertisesByTrilha(trilha.getId());
            List<Long> expertiseId = expertisePorTrilha.stream().map(ExpertisesPorTrilhaProjection::getId).toList();
            List<String> expertiseNome = expertisePorTrilha.stream().map(ExpertisesPorTrilhaProjection::getNome).toList();
            expertisesPorTrilha.put("id", expertiseId);
            expertisesPorTrilha.put("nome", expertiseNome);
            List<ProgressoExpertiseProjection> expertisesConcluidas = progressoColaboradorRepository
                    .findExpertisesByColaboradorId(colaboradorId, trilha.getId());
            List<Long> expertiseConcluidaId = expertisesConcluidas.stream()
                    .map(ProgressoExpertiseProjection::getExpertiseId).toList();
            List<String> expertiseConcluidaNome = expertisesConcluidas.stream()
                    .map(ProgressoExpertiseProjection::getNome).toList();
            expertisesConcluidasMap.put("id", expertiseConcluidaId);
            expertisesConcluidasMap.put("nome", expertiseConcluidaNome);

            dadosExpertisesConcluidasDto.setNomeTrilha(trilha.getWorkload());
            dadosExpertisesConcluidasDto.setExpertisesPorTrilha(expertisesPorTrilha);
            dadosExpertisesConcluidasDto.setExpertisesConcluidas(expertisesConcluidasMap);
            progressoTrilha.add(dadosExpertisesConcluidasDto);
        }
        return progressoTrilha;
    }
}
