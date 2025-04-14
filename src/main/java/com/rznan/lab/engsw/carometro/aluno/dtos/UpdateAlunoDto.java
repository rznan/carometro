package com.rznan.lab.engsw.carometro.aluno.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateAlunoDto(
        @NotBlank
        long id,
        long idFaculdade,
        long idCurso,
        String ra,
        String nome,
        int anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre
) {
}
