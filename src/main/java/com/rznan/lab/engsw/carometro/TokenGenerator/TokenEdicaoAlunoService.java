package com.rznan.lab.engsw.carometro.TokenGenerator;

import java.util.List;

public interface TokenEdicaoAlunoService extends TokenAlunoService<TokenEdicaoAluno> {
    public String gerarTokenEdicao(long idAluno);

    @Override
    TokenEdicaoAluno validarToken(String token);

    @Override
    void marcarComoUsado(TokenEdicaoAluno tokenEdicaoAluno);

    @Override
    void removerTokensExpirados();
}
