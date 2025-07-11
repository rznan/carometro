package com.rznan.lab.engsw.carometro.curso;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.curso.dtos.CreateCursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.UpdateCursoDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private String titulo;
    private LocalDate inauguracao;

    public Curso(CreateCursoDto dados) {
        this.titulo = dados.titulo();
        this.inauguracao = dados.inauguracao();
        this.alunos = new ArrayList<>();
    }

    public void update(UpdateCursoDto dados) {
        this.titulo = dados.titulo();
        this.inauguracao = dados.inauguracao();
    }

    public void addAluno(Aluno aluno) {
        aluno.setCurso(this);
        alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno) {
        aluno.setCurso(null);
        alunos.remove(aluno);
    }
}
