package com.rznan.lab.engsw.carometro.faculdade;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.aluno.AlunoDto;
import com.rznan.lab.engsw.carometro.curso.CursoDto;

import java.util.Date;
import java.util.List;

public record FaculdadeDto(
        Long id,
        List<CursoDto> cursos,
        List<AlunoDto> alunos,
        String nome,
        Date inauguracao
) {

    public static FaculdadeDto toDto(Faculdade faculdade) {
        return new FaculdadeDto(
                faculdade.getId(),
                faculdade.getCursos().stream().map(c -> CursoDto.toDto(c, false)).toList(),
                faculdade.getAlunos().stream().map(AlunoDto::toDto).toList(),
                faculdade.getNome(),
                faculdade.getInauguracao()
        );
    }


    public static FaculdadeDto toDto(Faculdade faculdade, boolean complete) {
        if(complete) {
            return toDto(faculdade);
        }
        return new FaculdadeDto(
                faculdade.getId(),
                null,
                null,
                faculdade.getNome(),
                faculdade.getInauguracao()
        );
    }
}
