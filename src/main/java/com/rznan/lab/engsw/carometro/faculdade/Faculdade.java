package com.rznan.lab.engsw.carometro.faculdade;

import com.rznan.lab.engsw.carometro.curso.Curso;
import com.rznan.lab.engsw.carometro.faculdade.dtos.CreateFaculdadeDto;
import com.rznan.lab.engsw.carometro.faculdade.dtos.UpdateFaculdadeDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private LocalDate inauguracao;

    public Faculdade(CreateFaculdadeDto dto) {
        this.nome = dto.nome();
        this.inauguracao = dto.inauguracao();
        this.cursos = new ArrayList<>();
    }

    public void update(UpdateFaculdadeDto dto) {
        this.nome = dto.nome();
        this.inauguracao = dto.inauguracao();
    }

    public void addCurso(Curso curso) {
        cursos.add(curso);
        curso.setFaculdade(this);
    }

    public void removeCurso(Curso curso) {
        curso.setFaculdade(null);
        cursos.remove(curso);
    }
}
