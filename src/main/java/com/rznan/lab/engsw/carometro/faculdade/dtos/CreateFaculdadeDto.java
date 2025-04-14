package com.rznan.lab.engsw.carometro.faculdade.dtos;

import java.util.Date;
import java.util.List;

public record CreateFaculdadeDto(
        List<Long> cursoIds,
        List<Long> alunoIds,
        String nome,
        Date inauguracao
) {
}