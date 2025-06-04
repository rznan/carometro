package com.rznan.lab.engsw.carometro.TokenGenerator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenEdicaoAlunoRepository extends JpaRepository<TokenEdicaoAluno,Long> {
    Optional<TokenEdicaoAluno> findByToken(String token);
}
