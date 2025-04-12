package com.rznan.lab.engsw.carometro.aluno.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoAluno(
        @NotBlank
        long id,
        @NotBlank
        long idCurso,
        @NotBlank
        long idFaculdade,
        String ra,
        String nome,
        int anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre
) {
}
