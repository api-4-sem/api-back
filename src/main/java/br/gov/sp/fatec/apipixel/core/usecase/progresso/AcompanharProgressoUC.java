package br.gov.sp.fatec.apipixel.core.usecase.progresso;

import br.gov.sp.fatec.apipixel.core.domain.command.AcompanharProgressoCommand;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosProgressoDto;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosProgressoTrilhaDto;
import br.gov.sp.fatec.apipixel.core.domain.entity.ProgressoColaborador;
import br.gov.sp.fatec.apipixel.core.domain.entity.Trilha;
import br.gov.sp.fatec.apipixel.core.domain.entity.TrilhaCurso;
import br.gov.sp.fatec.apipixel.core.domain.projection.ProgressoExpertiseProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.domain.repository.ProgressoColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.domain.repository.TrilhaCursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AcompanharProgressoUC {

    private final ProgressoColaboradorRepository progressoColaboradorRepository;

    private final TrilhaCursoRepository trilhaCurosRepository;

    private final ColaboradorRepository colaboradorRepository;

    public AcompanharProgressoUC(ProgressoColaboradorRepository progressoColaboradorRepository, TrilhaCursoRepository trilhaCurosRepository, ColaboradorRepository colaboradorRepository) {
        this.progressoColaboradorRepository = progressoColaboradorRepository;
        this.trilhaCurosRepository = trilhaCurosRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    @Transactional
    public DadosProgressoDto executar(AcompanharProgressoCommand command){
        List<ProgressoColaborador> progressos = progressoColaboradorRepository.carregar();
        List<ProgressoColaborador> progressoColaborador = progressos.stream()
                .filter(progresso -> progresso.getColaborador().getId().equals(command.getColaboradorId()))
                .collect(Collectors.toList());
        String nomeEmpresa = progressoColaborador.stream()
                .map(progresso -> progresso.getColaborador().getEmpresa().getNome())
                .findFirst()
                .orElse(null);
        String nomeColaborador = progressoColaborador.stream()
                .map(progresso -> progresso.getColaborador().getNome())
                .findFirst()
                .orElse(null);
        List<DadosProgressoTrilhaDto> dadosProgressoTrilhaDtos = obtertDataConclusaoTrilha(progressoColaborador,
                command.getColaboradorId());
        DadosProgressoDto dadosProgressoDto = new DadosProgressoDto();
        dadosProgressoDto.setNomeColaborador(nomeColaborador);
        dadosProgressoDto.setNomeEmpresa(nomeEmpresa);
        dadosProgressoDto.setDadosTrilha(dadosProgressoTrilhaDtos);
        return dadosProgressoDto;
    }

    public List<DadosProgressoTrilhaDto> obtertDataConclusaoTrilha(List<ProgressoColaborador> progressoColaborador, Long colaboradorId){
        List<TrilhaCurso> trilhaCursos = progressoColaborador.stream()
                .map(ProgressoColaborador::getTrilhaCurso)
                .toList();

        List<Trilha> trilhasDistintas = trilhaCursos.stream()
                .map(TrilhaCurso::getTrilha)
                .collect(Collectors.toMap(Trilha::getId, Function.identity(), (trilha1, trilha2) -> trilha1))
                .values().stream().toList();

        List<DadosProgressoTrilhaDto> progressoTrilha = new ArrayList<>();

        for(Trilha trilha : trilhasDistintas){
            // fazer consulta na trilha_curso para obter as expertises relacionadas a essa trilha
            List<Long> expertisePorTrilha = trilhaCurosRepository.findExpertisesByTrilhaId(trilha.getId());
            // criar consulta no progresso_colaborador para obter quais experties ele já fez
            List<ProgressoExpertiseProjection> expertisesConcluidas = progressoColaboradorRepository
                    .findExpertisesByColaboradorId(colaboradorId, trilha.getId());
            List<Long> expertiseConcluidaId = expertisesConcluidas.stream()
                    .map(ProgressoExpertiseProjection::getExpertiseId).toList();
            if(expertisePorTrilha.containsAll(expertiseConcluidaId)){
                DadosProgressoTrilhaDto dadosProgressoDto = new DadosProgressoTrilhaDto();
                dadosProgressoDto.setNomeTrilha(trilha.getWorkload());
                dadosProgressoDto.setValidadeTrilha(trilha.getValidadeLicenca());
                dadosProgressoDto.setDataConclusaoTrilha(expertisesConcluidas.stream()
                        .map(ProgressoExpertiseProjection::getDataFim)
                        .toList()
                        .stream()
                        .max(Comparator.naturalOrder())
                        .orElse(null));
                progressoTrilha.add(dadosProgressoDto);
            }
        }
        return progressoTrilha;
    }
}
