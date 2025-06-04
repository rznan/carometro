package com.rznan.lab.engsw.carometro.TokenGenerator;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "token_criacao_aluno")
@Entity(name = "token_criacao_aluno")
@Getter
@Setter
public class TokenCadastroAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private long id;

    private String token;

    private boolean usado = false;

    private LocalDateTime validoAte;


    public TokenCadastroAluno(String token, LocalDateTime validoAte) {
        this.token = token;
        this.validoAte = validoAte;
    }

    public boolean isValido() {
        return LocalDateTime.now().isAfter(validoAte) || usado;
    }

}
