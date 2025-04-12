package com.rznan.lab.engsw.carometro.curso;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.faculdade.Faculdade;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Table(name = "curso")
@Entity(name = "curso")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private long id;

    @OneToMany(mappedBy = "curso")
    private List<Aluno> alunos;
    @ManyToOne
    private Faculdade faculdade;

    private String titulo;
    private Date inauguracao;
}
