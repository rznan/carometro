package com.rznan.lab.engsw.carometro.aluno.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateAlunoDto(
        @NotBlank
        long id,
        long idFaculdade,
        long idCurso,
        String ra,
        String nome,
        LocalDate anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre
) {
}
