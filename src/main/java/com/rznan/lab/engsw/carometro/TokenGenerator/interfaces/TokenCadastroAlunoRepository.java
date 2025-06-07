package com.rznan.lab.engsw.carometro.TokenGenerator.interfaces;

import com.rznan.lab.engsw.carometro.TokenGenerator.TokenCadastroAluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenCadastroAlunoRepository extends JpaRepository<TokenCadastroAluno,Long> {
    Optional<TokenCadastroAluno> findByToken(String token);
}
