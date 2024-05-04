package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.usecase.relatorio.GerarRelatorioUC;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final GerarRelatorioUC gerarRelatorioUC;

    private static final String NOME_RELATORIO = "Relatorio.xlsx";

    @GetMapping("{idEmpresa}")
    public ResponseEntity<Resource> download(@PathVariable("idEmpresa") Long idEmpresa) throws IOException {
        InputStreamResource file = gerarRelatorioUC.executar(idEmpresa);
        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + NOME_RELATORIO)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
        return body;
    }
}
