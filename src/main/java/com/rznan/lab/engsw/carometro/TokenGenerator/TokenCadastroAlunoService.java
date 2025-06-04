package com.rznan.lab.engsw.carometro.TokenGenerator;

import java.util.List;

public interface TokenCadastroAlunoService extends TokenAlunoService<TokenCadastroAluno> {

    public List<String> gerarTokensNovos(int quantidade);

    @Override
    TokenCadastroAluno validarToken(String token);

    @Override
    void marcarComoUsado(TokenCadastroAluno tokenCadastroAluno);

    @Override
    void removerTokensExpirados();
}

