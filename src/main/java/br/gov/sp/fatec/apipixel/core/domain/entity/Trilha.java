package br.gov.sp.fatec.apipixel.core.domain.entity;

import br.gov.sp.fatec.apipixel.core.domain.enumerations.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trilha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workload;
    @Embedded
    private Categoria categoria;
}
