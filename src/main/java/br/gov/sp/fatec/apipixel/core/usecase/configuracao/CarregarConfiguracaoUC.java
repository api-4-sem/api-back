package br.gov.sp.fatec.apipixel.core.usecase.configuracao;

import br.gov.sp.fatec.apipixel.core.domain.entity.ConfiguracaoNotificacao;
import br.gov.sp.fatec.apipixel.core.domain.repository.ConfiguracaoNotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarregarConfiguracaoUC {

    private final ConfiguracaoNotificacaoRepository configuracaoNotificacaoRepository;

    public List<ConfiguracaoNotificacao> executar(){
        return configuracaoNotificacaoRepository.carregar();
    }

}
