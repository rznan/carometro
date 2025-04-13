package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.curso.CursoDto;
import com.rznan.lab.engsw.carometro.faculdade.FaculdadeDto;

public record AlunoDto(
        Long id,
        CursoDto curso,
        FaculdadeDto faculdade,
        String ra,
        String nome,
        int anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre
) {
        public static AlunoDto toDto(Aluno a) {
                return new AlunoDto(
                        a.getId(),
                        CursoDto.toDto(a.getCurso(), false),
                        FaculdadeDto.toDto(a.getFaculdade(), false),
                        a.getRa(),
                        a.getNome(),
                        a.getAnoEntrada(),
                        a.getHistorico(),
                        a.getComentarioFaculdade(),
                        a.getComentarioLivre()
                );
        }
}
