package com.rznan.lab.engsw.carometro.curso.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record UpdateCursoDto(

        @NotNull(message = "O ID do curso é obrigatório")
        Long id,

        @NotNull(message = "A lista de alunos não pode ser nula")
        List<@NotNull(message = "ID de aluno não pode ser nulo") Long> alunosId,

        @NotBlank(message = "O título do curso não pode estar vazio")
        @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
        String titulo,

        @NotNull(message = "A data de inauguração é obrigatória")
        @PastOrPresent(message = "A data de inauguração deve estar no passado ou ser hoje")
        LocalDate inauguracao

) {}
