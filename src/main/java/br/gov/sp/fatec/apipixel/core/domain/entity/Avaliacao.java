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
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataAvaliacao;
    private String feedback;
    private Double nota;

    @ManyToOne
    @JoinColumn(name="empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name="colaborador_id")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name="trilha_id")
    private Trilha trilha;

    @ManyToOne
    @JoinColumn(name="expertise_id")
    private Expertise expertise;
}
