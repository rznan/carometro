package com.rznan.lab.engsw.carometro.curso.dtos;

import com.rznan.lab.engsw.carometro.curso.Curso;

import java.time.LocalDate;

public record CursoDto(
        Long id,
        String titulo,
        LocalDate inauguracao
) {
    public CursoDto(Curso curso) {
            this(
                curso.getId(),
                curso.getTitulo(),
                curso.getInauguracao()
        );
    }
}