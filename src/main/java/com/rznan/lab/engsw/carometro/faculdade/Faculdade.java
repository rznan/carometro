package com.rznan.lab.engsw.carometro.faculdade;

import com.rznan.lab.engsw.carometro.curso.Curso;
import com.rznan.lab.engsw.carometro.faculdade.dtos.CreateFaculdadeDto;
import com.rznan.lab.engsw.carometro.faculdade.dtos.UpdateFaculdadeDto;
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

    public Faculdade(CreateFaculdadeDto dto) {
        this.nome = dto.nome();
        this.inauguracao = dto.inauguracao();
    }

    public void update(UpdateFaculdadeDto dto) {
        this.nome = dto.nome();
        this.inauguracao = dto.inauguracao();
    }
}
