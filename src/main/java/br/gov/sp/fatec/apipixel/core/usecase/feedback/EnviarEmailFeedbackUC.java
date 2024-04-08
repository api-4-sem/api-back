package br.gov.sp.fatec.apipixel.core.usecase.feedback;

import br.gov.sp.fatec.apipixel.core.domain.command.EnviarEmailCommand;
import br.gov.sp.fatec.apipixel.core.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EnviarEmailFeedbackUC {

    private final EmailService emailService;

    public EnviarEmailFeedbackUC(EmailService emailService) {
        this.emailService = emailService;
    }

    public void executar(EnviarEmailCommand command){
        emailService.sendSimpleMail(command);
    }
}
