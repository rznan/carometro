package com.rznan.lab.engsw.carometro.curso.dtos;

import java.util.Date;
import java.util.List;

public record CreateCursoDto(
        List<Long> alunosId,
        Long faculdadeId,
        String titulo,
        Date inauguracao
) {
}