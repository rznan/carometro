package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.UpdateAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import com.rznan.lab.engsw.carometro.curso.Curso;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

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
    private String nome;
    private String apelido;
    private Year anoFormado;
    private String sobreMimProfissional;
    private String sobreMimSocial;
    private String comentarioFaculdade;
    private String comentarioLivre;
    private String imagemPerfil;
    private List<String> fotos;
    private String linkedin;
    private boolean aprovado = false;

    public Aluno(CreateAlunoDto dados) {
        this.nome = dados.nome();
        this.apelido = dados.apelido();
        this.anoFormado = dados.anoFormado();
        this.sobreMimProfissional = dados.sobreMimProfissional();
        this.sobreMimSocial = dados.sobreMimSocial();
        this.comentarioFaculdade = dados.comentarioFaculdade();
        this.comentarioLivre = dados.comentarioLivre();
        this.imagemPerfil = dados.imagemPerfil();
        this.fotos = dados.fotos();
        this.linkedin = dados.linkedin();
    }

    public void update(UpdateAlunoDto dados) {
        // TODO: checar se os campos estão em branco
        this.nome = dados.nome();
        this.apelido = dados.apelido();
        this.anoFormado = dados.anoFormado();
        this.sobreMimProfissional = dados.sobreMimProfissional();
        this.sobreMimSocial = dados.sobreMimSocial();
        this.comentarioFaculdade = dados.comentarioFaculdade();
        this.comentarioLivre = dados.comentarioLivre();
        this.linkedin = dados.linkedin();


    }
}
