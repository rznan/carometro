package com.rznan.lab.engsw.carometro.aluno.dtos;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import jakarta.validation.constraints.NotBlank;

public record DetailsAlunoDto(
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
        int anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre
) {
        public DetailsAlunoDto(Aluno a) {
                this(
                        a.getId(),
                        a.getCurso().getId(),
                        a.getCurso().getTitulo(),
                        a.getFaculdade().getId(),
                        a.getFaculdade().getNome(),
                        a.getRa(),
                        a.getNome(),
                        a.getAnoEntrada(),
                        a.getHistorico(),
                        a.getComentarioFaculdade(),
                        a.getComentarioLivre()
                );
        }
}
