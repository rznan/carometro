package com.rznan.lab.engsw.carometro.faculdade.dtos;

import java.time.LocalDate;
import java.util.List;

public record UpdateFaculdadeDto(
        Long id,
        List<Long> cursoIds,
        String nome,
        LocalDate inauguracao
) {
}