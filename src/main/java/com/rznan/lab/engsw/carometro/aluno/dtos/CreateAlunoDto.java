package com.rznan.lab.engsw.carometro.aluno.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateAlunoDto(

        long idCurso,
        String ra,
        String nome,
        LocalDate anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre,
        String imagemPerfil
) {
}
