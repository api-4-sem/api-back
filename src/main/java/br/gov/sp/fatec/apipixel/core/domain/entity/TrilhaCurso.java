package br.gov.sp.fatec.apipixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrilhaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "expertise_id")
    private Expertise expertise;
    @ManyToOne
    @JoinColumn(name = "trilha_id")
    private Trilha trilha;
    @OneToMany(mappedBy = "trilhaCurso")
    private List<ProgressoColaborador> progresso;
}
