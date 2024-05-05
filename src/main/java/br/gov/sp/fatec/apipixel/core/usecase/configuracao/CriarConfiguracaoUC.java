package br.gov.sp.fatec.apipixel.core.usecase.configuracao;

import br.gov.sp.fatec.apipixel.core.domain.command.CadastrarConfiguracaoCommand;
import br.gov.sp.fatec.apipixel.core.domain.entity.ConfiguracaoNotificacao;
import br.gov.sp.fatec.apipixel.core.domain.repository.ConfiguracaoNotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriarConfiguracaoUC {

    private final ConfiguracaoNotificacaoRepository configuracaoNotificacaoRepository;

    public CriarConfiguracaoUC(ConfiguracaoNotificacaoRepository configuracaoNotificacaoRepository) {
        this.configuracaoNotificacaoRepository = configuracaoNotificacaoRepository;
    }

    public void executar(List<CadastrarConfiguracaoCommand> commandList){
        for(CadastrarConfiguracaoCommand command : commandList){
            ConfiguracaoNotificacao configuracaoNotificacao = configuracaoNotificacaoRepository
                    .carregarPorTipoNotificacao(command.getTipoNotificacao());
            if(configuracaoNotificacao != null){
                configuracaoNotificacao.setFrequencia(command.getFrequenciaDias());
                configuracaoNotificacaoRepository.salvar(configuracaoNotificacao);
            }
        }
    }

}
