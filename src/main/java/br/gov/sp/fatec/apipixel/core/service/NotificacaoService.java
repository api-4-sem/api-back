package br.gov.sp.fatec.apipixel.core.service;

import br.gov.sp.fatec.apipixel.core.domain.command.EnviarEmailCommand;
import br.gov.sp.fatec.apipixel.core.domain.entity.Colaborador;
import br.gov.sp.fatec.apipixel.core.domain.entity.ProgressoColaborador;
import br.gov.sp.fatec.apipixel.core.domain.projection.ColaboradorOciosoProjection;
import br.gov.sp.fatec.apipixel.core.domain.repository.ColaboradorRepository;
import br.gov.sp.fatec.apipixel.core.domain.repository.ProgressoColaboradorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class NotificacaoService {

    private final EmailService emailService;

    private final ProgressoColaboradorRepository progressoColaboradorRepository;

    private final ColaboradorRepository colaboradorRepository;

    @Scheduled(cron = "0 * * * * *")
    public void notifica() {
        List<ProgressoColaborador> progressoColaboradores = progressoColaboradorRepository.carregar();
        List<ProgressoColaborador> progressoOcioso = progressoColaboradorRepository.carregar().stream()
                .filter(progressoColaborador -> progressoColaborador.getDataFim() == null &&
                        LocalDateTime.now().isAfter(progressoColaborador.getDataInicio().plusDays(7L)))
                .toList();
        progressoOcioso.forEach(progressoColaborador -> emailService
                .sendSimpleMail(new EnviarEmailCommand(progressoColaborador.getColaborador().getEmail(),
                    getTemplateEmail(), "Notificação Oracle", null)));
        log.info("notificando");
    }

    private String getTemplateEmail(){
        return """
                Olá, percebemos que não há avanço em sua trajetória de aprendizado há mais de uma semana.     
                Ressaltamos que é fundamental manter um ritmo constante de progresso para garantir que você alcance seus objetivos de aprendizado e contribua para o seu crescimento profissional.              
                Se você estiver enfrentando algum desafio ou precisar de suporte adicional para avançar na trilha de aprendizado, por favor, não hesite em entrar em contato. Estamos aqui para ajudar e fornecer o apoio necessário para garantir o seu sucesso.
                """;
    }
}
