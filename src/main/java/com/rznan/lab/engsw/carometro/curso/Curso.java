package com.rznan.lab.engsw.carometro.curso;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.aluno.dtos.UpdateAlunoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.CreateCursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.UpdateCursoDto;
import com.rznan.lab.engsw.carometro.faculdade.Faculdade;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate inauguracao;

    public Curso(CreateCursoDto dados) {
        this.titulo = dados.titulo();
        this.inauguracao = dados.inauguracao();
    }

    public void update(UpdateCursoDto dados) {
        this.titulo = dados.titulo();
        this.inauguracao = dados.inauguracao();
    }
}
