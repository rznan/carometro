package com.rznan.lab.engsw.carometro.curso.dtos;

import java.time.LocalDate;
import java.util.List;

public record CreateCursoDto(
        List<Long> alunosId,
        Long faculdadeId,
        String titulo,
        LocalDate inauguracao
) {
}