package br.gov.sp.fatec.apipixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    @OneToMany(mappedBy = "colaborador")
    private List<ProgressoColaborador> progresso;
}
