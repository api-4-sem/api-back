package br.gov.sp.fatec.apipixel.core.domain.command;

import br.gov.sp.fatec.apipixel.core.domain.enumerations.TipoNotificacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarConfiguracaoCommand {

    private Long frequenciaDias;
    private TipoNotificacao tipoNotificacao;
}
