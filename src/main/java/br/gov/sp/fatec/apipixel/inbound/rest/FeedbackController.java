package br.gov.sp.fatec.apipixel.inbound.rest;

import br.gov.sp.fatec.apipixel.core.domain.command.EnviarEmailCommand;
import br.gov.sp.fatec.apipixel.core.domain.command.EnviarFeedbackCommand;
import br.gov.sp.fatec.apipixel.core.usecase.feedback.EnviarEmailFeedbackUC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/feedback")
@CrossOrigin("*")
public class FeedbackController {

    private final EnviarEmailFeedbackUC enviarEmailFeedbackUC;

    public FeedbackController(EnviarEmailFeedbackUC enviarEmailFeedbackUC) {
        this.enviarEmailFeedbackUC = enviarEmailFeedbackUC;
    }

    @PostMapping("{colaboradorId}")
    private ResponseEntity<Void> enviarFeedback(@PathVariable("colaboradorId") Long colaboradorId){
        enviarEmailFeedbackUC.executar(new EnviarFeedbackCommand(colaboradorId));
        return ResponseEntity.ok().build();
    }
}
