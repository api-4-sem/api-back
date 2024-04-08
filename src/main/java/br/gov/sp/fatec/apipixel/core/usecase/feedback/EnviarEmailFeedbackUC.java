package br.gov.sp.fatec.apipixel.core.usecase.feedback;

import br.gov.sp.fatec.apipixel.core.domain.command.EnviarEmailCommand;
import br.gov.sp.fatec.apipixel.core.domain.command.EnviarFeedbackCommand;
import br.gov.sp.fatec.apipixel.core.domain.entity.Colaborador;
import br.gov.sp.fatec.apipixel.core.domain.repository.ColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnviarEmailFeedbackUC {

    private final EmailService emailService;
    private final ColaboradorRepository colaboradorRepository;

    public EnviarEmailFeedbackUC(EmailService emailService, ColaboradorRepository colaboradorRepository) {
        this.emailService = emailService;
        this.colaboradorRepository = colaboradorRepository;
    }

    public void executar(EnviarFeedbackCommand command){
        Optional<Colaborador> colaboradorOpt = colaboradorRepository.carregarColaborador
                (command.getColaboradorId());
        Colaborador colaborador = colaboradorOpt.get();
        EnviarEmailCommand emailCommand = EnviarEmailCommand.builder()
                        .recipient(colaborador.getEmail())
                        .subject("Solicitação Feedback")
                        .msgBody(getTemplateEmail(colaborador.getNome()))
                        .build();
        emailService.sendSimpleMail(emailCommand);
    }

    private String getTemplateEmail(String nomeUsuario){
        String mensagem = "Prezado " + nomeUsuario + ",\n\n" +
                "Espero que esteja tudo bem!\n\n" +
                "Estamos comprometidos em melhorar continuamente nossa plataforma de ensino para garantir que atenda às suas necessidades da melhor maneira possível. Valorizamos muito a sua opinião e gostaríamos de solicitar seu feedback sobre sua experiência até agora.\n\n" +
                "Por favor, reserve alguns minutos do seu tempo para nos fornecer seus comentários e sugestões. Sua contribuição nos ajudará a identificar áreas de melhoria e aprimorar a plataforma para todos os usuários.";
        return mensagem;
    }
}
