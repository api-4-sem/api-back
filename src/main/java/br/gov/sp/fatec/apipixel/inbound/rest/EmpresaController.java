package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.command.CadastrarEmpresaCommand;
import br.gov.sp.fatec.apipixel.core.domain.entity.Empresa;
import br.gov.sp.fatec.apipixel.core.domain.projection.EmpresaProjection;
import br.gov.sp.fatec.apipixel.core.domain.projection.StatAvaliacaoPorEmpresa;
import br.gov.sp.fatec.apipixel.core.domain.repository.ExpertiseRepository;
import br.gov.sp.fatec.apipixel.core.usecase.empresa.CarregarEmpresaUC;
import br.gov.sp.fatec.apipixel.core.usecase.empresa.CriarEmpresaUC; // Importar a classe correta
import br.gov.sp.fatec.apipixel.outbound.jpa.EmpresaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaJpaRepository empresaRepository;
    private final ExpertiseRepository expertiseRepository;
    private final CarregarEmpresaUC carregarEmpresaUC;
    private final CriarEmpresaUC criarEmpresaUC;

    @GetMapping("/carregar-empresas")
    public ResponseEntity<List<EmpresaProjection>> findAll() {
        return ResponseEntity.ok(carregarEmpresaUC.executar());
    }


    @PostMapping("/criar-empresas")
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody CadastrarEmpresaCommand command) {
        Empresa empresaCriada = criarEmpresaUC.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCriada);
    }

    @GetMapping("/empresas-por-estado")
    public Map<String, Long> getNumeroDeEmpresasPorEstado() {
        List<Object[]> results = empresaRepository.countEmpresasByEstado();
        Map<String, Long> empresasPorEstado = new HashMap<>();

        for (Object[] result : results) {
            String estado = (String) result[0];
            Long count = (Long) result[1];
            empresasPorEstado.put(estado, count);
        }

        return empresasPorEstado;
    }

    @GetMapping("/avaliacao-por-trilha-expertise")
    public List<StatAvaliacaoPorEmpresa> getAvaliacoes(@RequestParam Long trilhaId, @RequestParam Long expertiseId) {
        return expertiseRepository.findEmpresaIdCountAndAvgNotaByTrilhaIdAndExpertiseId(trilhaId, expertiseId);
    }
}
