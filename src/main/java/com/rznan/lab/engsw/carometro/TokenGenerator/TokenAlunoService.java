package com.rznan.lab.engsw.carometro.TokenGenerator;

import java.util.List;

public interface TokenAlunoService<T> {
    public T validarToken(String token);
    public void marcarComoUsado(T t);
    public void removerTokensExpirados();
}
