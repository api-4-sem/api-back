package br.gov.sp.fatec.apipixel.core.service;

import br.gov.sp.fatec.apipixel.core.domain.command.EnviarEmailCommand;

public interface EmailService {

    void sendSimpleMail(EnviarEmailCommand enviarEmailCommand);
}
