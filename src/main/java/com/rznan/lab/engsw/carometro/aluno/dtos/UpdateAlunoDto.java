package com.rznan.lab.engsw.carometro.aluno.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateAlunoDto(

        @NotNull(message = "O ID do aluno é obrigatório")
        long id,
        @NotNull(message = "Curso é obrigatório")
        long idCurso,
        @NotBlank(message = "O RA do aluno não pode estar vazio")
        String ra,
        @NotBlank(message = "O nome do aluno é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotNull(message = "O ano de entrada é obrigatória")
        @PastOrPresent(message = "O ano de entrada deve estar no passado ou ser hoje")
        LocalDate anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre
) {
}
