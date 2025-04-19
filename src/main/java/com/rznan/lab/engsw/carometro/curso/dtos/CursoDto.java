package com.rznan.lab.engsw.carometro.curso.dtos;

import com.rznan.lab.engsw.carometro.curso.Curso;

import java.time.LocalDate;

public record CursoDto(
        Long id,
        Long faculdadeId,
        String faculdadeNome,
        String titulo,
        LocalDate inauguracao
) {
    public CursoDto(Curso curso) {
            this(
                curso.getId(),
                curso.getFaculdade() != null ? curso.getFaculdade().getId() : -1,
                curso.getFaculdade() != null ? curso.getFaculdade().getNome() : "sem faculdade",
                curso.getTitulo(),
                curso.getInauguracao()
        );
    }
}