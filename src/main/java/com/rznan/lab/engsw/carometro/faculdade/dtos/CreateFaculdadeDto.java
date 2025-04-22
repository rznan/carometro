package com.rznan.lab.engsw.carometro.faculdade.dtos;


import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.List;

public record CreateFaculdadeDto(


        @NotNull(message = "A lista de cursos não pode ser nula")
        List<@NotNull(message = "ID de curso não pode ser nulo") Long> cursoIds,

        @NotBlank(message = "O nome da faculdade é obrigatório")
        @Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres")
        String nome,

        @NotNull(message = "A data de inauguração é obrigatória")
        @PastOrPresent(message = "A data de inauguração deve estar no passado ou ser hoje")
        LocalDate inauguracao

) {}
