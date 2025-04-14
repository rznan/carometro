package com.rznan.lab.engsw.carometro.curso.dtos;

import java.util.Date;
import java.util.List;

public record UpdateCursoDto(
        Long id,
        List<Long> alunosId,
        Long faculdadeId,
        String titulo,
        Date inauguracao
) {
}