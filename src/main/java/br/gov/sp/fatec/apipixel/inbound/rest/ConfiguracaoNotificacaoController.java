package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.command.CadastrarConfiguracaoCommand;
import br.gov.sp.fatec.apipixel.core.domain.entity.ConfiguracaoNotificacao;
import br.gov.sp.fatec.apipixel.core.usecase.configuracao.CarregarConfiguracaoUC;
import br.gov.sp.fatec.apipixel.core.usecase.configuracao.CriarConfiguracaoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/configuracao")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ConfiguracaoNotificacaoController {

    private final CarregarConfiguracaoUC carregarConfiguracaoUC;
    private final CriarConfiguracaoUC criarConfiguracaoUC;

    @GetMapping
    public ResponseEntity<List<ConfiguracaoNotificacao>> listarConfiguracoes(){
        return ResponseEntity.ok(carregarConfiguracaoUC.executar());
    }

    @PutMapping
    public ResponseEntity alterarConfiguracoes(@RequestBody List<CadastrarConfiguracaoCommand>commandList){
        criarConfiguracaoUC.executar(commandList);
        return ResponseEntity.ok().build();
    }

}
