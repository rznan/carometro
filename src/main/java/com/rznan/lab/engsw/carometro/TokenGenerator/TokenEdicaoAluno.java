package com.rznan.lab.engsw.carometro.TokenGenerator;


import com.rznan.lab.engsw.carometro.aluno.Aluno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "token_edicao_aluno")
@Entity(name = "token_edicao_aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenEdicaoAluno {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private long id;

    private String token;

    private boolean usado = false;

    private LocalDateTime validoAte;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;


    public TokenEdicaoAluno(String token, LocalDateTime validoAte, Aluno aluno) {
        this.token = token;
        this.validoAte = validoAte;
        this.aluno = aluno;
    }

    public boolean isValido() {
        return LocalDateTime.now().isAfter(validoAte) || usado;
    }

}
