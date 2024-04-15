package br.gov.sp.fatec.apipixel.core.service.impl;

import br.gov.sp.fatec.apipixel.core.domain.command.EnviarEmailCommand;
import br.gov.sp.fatec.apipixel.core.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final String SENDER = "alertaoracle@gmail.com";
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMail(EnviarEmailCommand command)
    {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            mailMessage.setFrom(SENDER);
            mailMessage.setTo(command.getRecipient());
            mailMessage.setText(command.getMsgBody());
            mailMessage.setSubject(command.getSubject());
            javaMailSender.send(mailMessage);
            log.info("Email enviado com sucesso");
        }
        catch (Exception e) {
            log.info("Erro ao enviar email: {}", e.getCause());
        }
    }
}
