package com.rznan.lab.engsw.carometro.aluno.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public record CreateAlunoDto(
        @NotNull(message = "Curso é obrigatório")
        long idCurso,

        @NotBlank(message = "O nome do aluno é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        String apelido,
        @NotNull(message = "O ano de formado é obrigatória")
        @PastOrPresent(message = "O ano de formado deve estar no passado ou ser hoje")
        Year anoFormado,
        String sobreMimProfissional,
        String sobreMimSocial,
        String comentarioFaculdade,
        String comentarioLivre,

        String imagemPerfil,
        List<String> fotos,
        @URL(message = "Url Invalida")
        String linkedin
) {
}
