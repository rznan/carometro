package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.UpdateAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import com.rznan.lab.engsw.carometro.curso.Curso;
import com.rznan.lab.engsw.carometro.faculdade.Faculdade;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "aluno")
@Entity(name = "aluno")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Aluno {
    //TODO: criar campo para dizer se foi aprovado ou não ou fazer o registro de solicitações de maneira diferente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private long id;

    @ManyToOne
    private Curso curso;

    private String ra;
    private String nome;
    private LocalDate anoEntrada;
    private String historico;
    private String comentarioFaculdade;
    private String comentarioLivre;

    public Aluno(CreateAlunoDto dados) {
        this.nome = dados.nome();
        this.ra = dados.ra();
        this.anoEntrada = dados.anoEntrada();
        this.historico = dados.historico();
        this.comentarioFaculdade = dados.comentarioFaculdade();
        this.comentarioLivre = dados.comentarioLivre();
    }

    public void update(UpdateAlunoDto dados) {
        // TODO: checar se os campos estão em branco
        this.nome = dados.nome();
        this.ra = dados.ra();
        this.anoEntrada = dados.anoEntrada();
        this.historico = dados.historico();
        this.comentarioFaculdade = dados.comentarioFaculdade();
        this.comentarioLivre = dados.comentarioLivre();
    }
}
