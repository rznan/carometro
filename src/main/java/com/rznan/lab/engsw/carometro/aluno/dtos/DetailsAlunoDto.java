package com.rznan.lab.engsw.carometro.aluno.dtos;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.curso.dtos.CursoDto;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DetailsAlunoDto(

        long id,

        CursoDto curso,
        String ra,
        String nome,
        LocalDate anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre
) {
        public DetailsAlunoDto(Aluno a) {
                this(
                        a.getId(),
                        a.getCurso() != null ? new CursoDto(a.getCurso()) : null,
                        a.getRa(),
                        a.getNome(),
                        a.getAnoEntrada(),
                        a.getHistorico(),
                        a.getComentarioFaculdade(),
                        a.getComentarioLivre()
                );
        }
}
