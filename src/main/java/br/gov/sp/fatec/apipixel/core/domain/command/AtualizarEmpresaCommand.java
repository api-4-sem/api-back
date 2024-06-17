package br.gov.sp.fatec.apipixel.core.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarEmpresaCommand {

    private String adminNome;
    private String adminEmail;
    private String cidade;
    private Long codigo;
    private String estado;
    private String nome;
    private String pais;
}
