package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.command.EnviarEmailCommand;
import br.gov.sp.fatec.apipixel.core.usecase.feedback.EnviarEmailFeedbackUC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {

    private final EnviarEmailFeedbackUC enviarEmailFeedbackUC;

    public FeedbackController(EnviarEmailFeedbackUC enviarEmailFeedbackUC) {
        this.enviarEmailFeedbackUC = enviarEmailFeedbackUC;
    }

    @PostMapping
    private void enviarFeedback(@RequestBody EnviarEmailCommand enviarEmailCommand){
        enviarEmailFeedbackUC.executar(enviarEmailCommand);
    }
}
