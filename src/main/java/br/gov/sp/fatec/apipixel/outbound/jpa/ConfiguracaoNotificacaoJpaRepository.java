package br.gov.sp.fatec.apipixel.outbound.jpa;

import br.gov.sp.fatec.apipixel.core.domain.entity.ConfiguracaoNotificacao;
import br.gov.sp.fatec.apipixel.core.domain.enumerations.TipoNotificacao;
import br.gov.sp.fatec.apipixel.core.domain.repository.ConfiguracaoNotificacaoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ConfiguracaoNotificacaoJpaRepository extends JpaRepository<ConfiguracaoNotificacao, Long>, ConfiguracaoNotificacaoRepository {

    default List<ConfiguracaoNotificacao> carregar(){
        return findAll();
    }

    default ConfiguracaoNotificacao salvar(ConfiguracaoNotificacao configuracaoNotificacao){
        return save(configuracaoNotificacao);
    }

    default ConfiguracaoNotificacao carregarPorTipoNotificacao(TipoNotificacao tipoNotificacao){
        return findByTipo(tipoNotificacao);
    }

    ConfiguracaoNotificacao findByTipo(TipoNotificacao tipoNotificacao);
}
