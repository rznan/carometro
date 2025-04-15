package com.rznan.lab.engsw.carometro.faculdade;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.curso.Curso;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Table(name = "faculdade")
@Entity(name = "faculdade")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Faculdade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculdade_id")
    private long id;

    @OneToMany(mappedBy = "faculdade")
    private List<Curso> cursos;

    private String nome;
    private Date inauguracao;
}
