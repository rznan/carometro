package com.rznan.lab.engsw.carometro.aluno.dtos;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AlunoDto(

        long id,

        long idCurso,
        String tituloCurso,

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
                        a.getCurso() != null ? a.getCurso().getId() : -1,
                        a.getCurso() != null ? a.getCurso().getTitulo() : "Não encontrado",
                        a.getCurso() != null ? a.getCurso().getFaculdade() != null ? a.getCurso().getFaculdade().getId() : -1 : -1,
                        a.getCurso() != null ? a.getCurso().getFaculdade() != null ? a.getCurso().getFaculdade().getNome() : "Faculdade não encontrada" : "Curso não encontrado",
                        a.getRa(),
                        a.getNome(),
                        a.getAnoEntrada(),
                        a.getHistorico(),
                        a.getComentarioFaculdade(),
                        a.getComentarioLivre()
                );
        }
}
