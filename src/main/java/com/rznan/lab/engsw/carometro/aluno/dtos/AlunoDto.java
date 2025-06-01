package com.rznan.lab.engsw.carometro.aluno.dtos;

import com.rznan.lab.engsw.carometro.aluno.Aluno;

import java.time.LocalDate;

public record AlunoDto(

        long id,

        long idCurso,
        String tituloCurso,

        String ra,
        String nome,
        LocalDate anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre,
        String imagemPerfil
) {
        public AlunoDto(Aluno a) {
                this(
                        a.getId(),
                        a.getCurso() != null ? a.getCurso().getId() : -1,
                        a.getCurso() != null ? a.getCurso().getTitulo() : "Não encontrado",
                        a.getRa(),
                        a.getNome(),
                        a.getAnoEntrada(),
                        a.getHistorico(),
                        a.getComentarioFaculdade(),
                        a.getComentarioLivre(),
                        a.getImagemPerfil()
                );
        }
}
