package br.gov.sp.fatec.apipixel.core.domain.repository;

import br.gov.sp.fatec.apipixel.core.domain.entity.ConfiguracaoNotificacao;
import br.gov.sp.fatec.apipixel.core.domain.enumerations.TipoNotificacao;

import java.util.List;

public interface ConfiguracaoNotificacaoRepository {

    List<ConfiguracaoNotificacao> carregar();

    ConfiguracaoNotificacao salvar(ConfiguracaoNotificacao configuracaoNotificacao);

    ConfiguracaoNotificacao carregarPorTipoNotificacao(TipoNotificacao tipoNotificacao);
}
