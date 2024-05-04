package br.gov.sp.fatec.apipixel.core.usecase.avaliacao;

import br.gov.sp.fatec.apipixel.core.domain.command.EnviarEmailCommand;
import br.gov.sp.fatec.apipixel.core.domain.entity.Avaliacao;
import br.gov.sp.fatec.apipixel.core.domain.entity.Colaborador;
import br.gov.sp.fatec.apipixel.core.domain.entity.Empresa;
import br.gov.sp.fatec.apipixel.core.domain.projection.EmpresaProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.domain.repository.EmpresaRepository;
import br.gov.sp.fatec.apipixel.core.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificarEmpresaDaAvaliacaoUC {

    private final EmailService emailService;
    private final EmpresaRepository empresaRepository;
    private final ColaboradorRepository colaboradorRepository;

    public NotificarEmpresaDaAvaliacaoUC(EmailService emailService, EmpresaRepository empresaRepository, ColaboradorRepository colaboradorRepository) {
        this.emailService = emailService;
        this.empresaRepository = empresaRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    public void executar(Avaliacao avaliacao){
        Optional<Empresa> empresaOpt = empresaRepository.carregarEmpresa(avaliacao.getEmpresaId());
        Empresa empresa = empresaOpt.get();

        Optional<Colaborador> colaboradorOpt = colaboradorRepository.carregarColaborador(avaliacao.getColaboradorId());
        Colaborador colaborador = colaboradorOpt.get();

        EnviarEmailCommand emailCommand = EnviarEmailCommand.builder()
                        .recipient(empresa.getAdminEmail())
                        .subject("Você recebeu uma nova avaliação!")
                        .msgBody(getTemplateEmail(colaborador.getNome(), avaliacao.getFeedback(), avaliacao.getNota()))
                        .build();
        emailService.sendSimpleMail(emailCommand);
    }

    private String getTemplateEmail(String nomeColaborador, String feedback, Double nota){
        String mensagem = "Voce recebeu uma nova avaliação de: " + nomeColaborador + ",\n\n" +
                "Feedback: \n" + feedback + "\n\n" +
                "Nota: " + nota + "\n\n";
        return mensagem;
    }
}
