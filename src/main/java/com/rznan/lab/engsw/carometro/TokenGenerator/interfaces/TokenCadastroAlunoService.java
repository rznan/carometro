package com.rznan.lab.engsw.carometro.TokenGenerator.interfaces;

import com.rznan.lab.engsw.carometro.TokenGenerator.TokenCadastroAluno;

import java.util.List;

public interface TokenCadastroAlunoService extends TokenAlunoService {

    public List<String> gerarTokensNovos(int quantidade);

}

