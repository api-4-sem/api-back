package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.entity.Empresa;
import br.gov.sp.fatec.apipixel.core.domain.projection.EmpresaProjection;
import br.gov.sp.fatec.apipixel.core.usecase.empresa.CarregarEmpresaUC;
import br.gov.sp.fatec.apipixel.core.usecase.empresa.CriarEmpresaUC; // Importar a classe correta
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
@RequiredArgsConstructor
public class EmpresaController {

    private final CarregarEmpresaUC carregarEmpresaUC;
    private final CriarEmpresaUC criarEmpresaUC; 

    @GetMapping("/carregar-empresas")
    public ResponseEntity<List<EmpresaProjection>> findAll() {
        return ResponseEntity.ok(carregarEmpresaUC.executar());
    }

  
    @PostMapping("/criar-empresas")
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa novaEmpresa) {
        Empresa empresaCriada = criarEmpresaUC.executar(novaEmpresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCriada);
    }
}
