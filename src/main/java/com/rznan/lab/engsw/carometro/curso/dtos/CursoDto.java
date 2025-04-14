package com.rznan.lab.engsw.carometro.curso.dtos;

import com.rznan.lab.engsw.carometro.curso.Curso;

import java.util.Date;

public record CursoDto(
        Long id,
        Long faculdadeId,
        String faculdadeNome,
        String titulo,
        Date inauguracao
) {
    public CursoDto(Curso curso) {
            this(
                curso.getId(),
                curso.getFaculdade().getId(),
                curso.getFaculdade().getNome(),
                curso.getTitulo(),
                curso.getInauguracao()
        );
    }
}