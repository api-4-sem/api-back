package br.gov.sp.fatec.apipixel.core.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnviarEmailCommand {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
