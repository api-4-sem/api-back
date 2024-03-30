package br.gov.sp.fatec.apipixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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
    @OneToMany
    @JoinColumn(name = "expertise_id")
    private Set<Expertise> expertise = new HashSet<>();
    @OneToMany
    @JoinColumn(name = "trilha_id")
    private Set<Trilha> trilha = new HashSet<>();
}
