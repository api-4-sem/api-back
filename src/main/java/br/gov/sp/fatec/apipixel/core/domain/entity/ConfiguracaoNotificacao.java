package br.gov.sp.fatec.apipixel.core.domain.entity;

import br.gov.sp.fatec.apipixel.core.domain.enumerations.TipoNotificacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONFIGURACAO_NOTIFICACAO")
public class ConfiguracaoNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long frequencia;
    @Enumerated(EnumType.STRING)
    private TipoNotificacao tipo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
