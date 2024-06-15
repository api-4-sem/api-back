package br.gov.sp.fatec.apipixel.core.domain.command;

import br.gov.sp.fatec.apipixel.core.domain.enumerations.TipoNotificacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarEmpresaCommand {

    private Long codigo;
    private String nome;
    private String cidade;
    private String estado;
    private String pais;
    private String adminNome;
    private String adminEmail;
}
