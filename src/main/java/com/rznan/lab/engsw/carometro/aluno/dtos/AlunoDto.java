package com.rznan.lab.engsw.carometro.aluno.dtos;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AlunoDto(
        @NotBlank
        long id,
        @NotBlank
        long idCurso,
        String tituloCurso,
        @NotBlank
        long idFaculdade,
        String nomeFaculdade,
        String ra,
        String nome,
        LocalDate anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre
) {
        public AlunoDto(Aluno a) {
                this(
                        a.getId(),
                        a.getCurso().getId(),
                        a.getCurso().getTitulo(),
                        a.getCurso().getFaculdade().getId(),
                        a.getCurso().getFaculdade().getNome(),
                        a.getRa(),
                        a.getNome(),
                        a.getAnoEntrada(),
                        a.getHistorico(),
                        a.getComentarioFaculdade(),
                        a.getComentarioLivre()
                );
        }
}
