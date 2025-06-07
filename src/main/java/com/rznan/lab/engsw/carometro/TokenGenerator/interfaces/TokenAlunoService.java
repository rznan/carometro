package com.rznan.lab.engsw.carometro.TokenGenerator.interfaces;

public interface TokenAlunoService<T> {
    public boolean validarToken(String token);
    public void marcarComoUsado(T t);
    public void removerTokensExpirados();
}
