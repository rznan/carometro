package com.rznan.lab.engsw.carometro.TokenGenerator.interfaces;

import com.rznan.lab.engsw.carometro.TokenGenerator.TokenEdicaoAluno;

public interface TokenEdicaoAlunoService extends TokenAlunoService<TokenEdicaoAluno> {
    public String gerarTokenEdicao(long idAluno);

}
