package br.gov.sp.fatec.apipixel.core.domain.entity;

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
public class AvaliacaoEmpresa {

    @Id
    private Long id;
    private Long empresaId;
    private LocalDateTime dataAvaliacao;
    private Long nota;
    private String comentario;
}
