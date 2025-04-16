package com.rznan.lab.engsw.carometro.faculdade.dtos;

import com.rznan.lab.engsw.carometro.curso.Curso;
import com.rznan.lab.engsw.carometro.faculdade.Faculdade;

import java.util.Date;
import java.util.List;

public record UpdateFaculdadeDto(
        Long id,
        List<Long> cursoIds,
        String nome,
        Date inauguracao
) {
}