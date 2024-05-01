package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.entity.Empresa;
import br.gov.sp.fatec.apipixel.core.domain.projection.EmpresaProjection;
import br.gov.sp.fatec.apipixel.core.usecase.empresa.CarregarEmpresaUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.gov.sp.fatec.apipixel.core.domain.projection.EmpresaProjection;
import br.gov.sp.fatec.apipixel.core.usecase.empresa.CarregarEmpresaUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/carregar-empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final CarregarEmpresaUC carregarEmpresaUC;
    private final CarregarEmpresaUC criarEmpresaUC;


    @GetMapping
    public ResponseEntity<List<EmpresaProjection>> findAll(){
        return ResponseEntity.ok(carregarEmpresaUC.executar());
    }

    @PostMapping
    public ResponseEntity<Empresa> criarEmpresa() {
        Empresa empresaCriada = (Empresa) criarEmpresaUC.executar();
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCriada);
    }

}
