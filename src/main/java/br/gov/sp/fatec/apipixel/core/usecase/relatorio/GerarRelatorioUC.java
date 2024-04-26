package br.gov.sp.fatec.apipixel.core.usecase.relatorio;

import br.gov.sp.fatec.apipixel.core.domain.dto.DadosRelatorio;
import br.gov.sp.fatec.apipixel.core.service.RelatorioService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GerarRelatorioUC {

    private final RelatorioService relatorioService;

    public GerarRelatorioUC(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    public InputStreamResource executar(Long idEmpresa) throws IOException {
        // obtem os dados necessario para popular o relatorio
        // chama o service
        List<DadosRelatorio> dadosRelatorio = new ArrayList<DadosRelatorio>();

        return RelatorioService.dataToExcel(dadosRelatorio);
    }
}
