package br.gov.sp.fatec.apipixel.core.usecase.dashboard;

import br.gov.sp.fatec.apipixel.core.domain.command.CarregarDadosExpertiseCommand;
import br.gov.sp.fatec.apipixel.core.domain.dto.DadosExpertisesDto;
import br.gov.sp.fatec.apipixel.core.domain.entity.Expertise;
import br.gov.sp.fatec.apipixel.core.domain.entity.ProgressoColaborador;
import br.gov.sp.fatec.apipixel.core.domain.entity.TrilhaCurso;
import br.gov.sp.fatec.apipixel.core.domain.repository.ProgressoColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.domain.repository.TrilhaCursoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarregarDadosExpertiseUC {

    private final TrilhaCursoRepository trilhaCursoRepository;
    private final ProgressoColaboradorRepository progressoColaboradorRepository;

    public CarregarDadosExpertiseUC(TrilhaCursoRepository trilhaCursoRepository,
                                    ProgressoColaboradorRepository progressoColaboradorRepository) {
        this.trilhaCursoRepository = trilhaCursoRepository;
        this.progressoColaboradorRepository = progressoColaboradorRepository;
    }

    public List<DadosExpertisesDto> executar(CarregarDadosExpertiseCommand command){
        List<DadosExpertisesDto> dadosExpertisesDto = new ArrayList<>();
        List<Expertise> expertises = trilhaCursoRepository.carregarExpertisesPorTrilha(command.getTrilhaId())
                .stream()
                .map(TrilhaCurso::getExpertise).toList();
        List<ProgressoColaborador> progressoColaboradores = progressoColaboradorRepository.carregar()
                .stream()
                .filter(progresso -> progresso.getColaborador().getEmpresa().getId().equals(command.getEmpresaId()) &&
                        progresso.getTrilhaCurso().getTrilha().getId().equals(command.getTrilhaId()))
                .toList();
        for(Expertise expertise : expertises){
            List<ProgressoColaborador> progressoTotal = progressoColaboradores.stream()
                    .filter(progresso -> progresso.getTrilhaCurso().getExpertise().getId().equals(expertise.getId())).toList();
            List<ProgressoColaborador> progressoConcluido = progressoColaboradores
                    .stream()
                    .filter(progresso -> progresso.getDataFim() != null &&
                            progresso.getTrilhaCurso().getExpertise().getId().equals(expertise.getId())).toList();
            DadosExpertisesDto.ProgressoExpertiseDto progressoExpertiseDto = new DadosExpertisesDto.ProgressoExpertiseDto();
            progressoExpertiseDto.setExpertiseId(expertise.getId());
            if(progressoConcluido.size() == progressoTotal.size()){
                progressoExpertiseDto.setStatus(DadosExpertisesDto.StatusExpertise.CONCLUIDO);
            }else{
                progressoExpertiseDto.setStatus(DadosExpertisesDto.StatusExpertise.NAO_CONCLUIDO);
            }
            Map<String, DadosExpertisesDto.ProgressoExpertiseDto> map = new HashMap<>();
            map.put(expertise.getNome(), progressoExpertiseDto);
            DadosExpertisesDto dados = new DadosExpertisesDto(map);
            dadosExpertisesDto.add(dados);
        }
        return dadosExpertisesDto;
    }
}
