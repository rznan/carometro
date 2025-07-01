package com.rznan.lab.engsw.carometro.TokenGenerator.interfaces;

public interface TokenAlunoService {
    public boolean validarToken(String token);
    public void marcarComoUsado(String token);
    public void removerTokensExpirados();
}
