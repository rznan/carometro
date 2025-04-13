package com.rznan.lab.engsw.carometro.curso;

import com.rznan.lab.engsw.carometro.aluno.AlunoDto;
import com.rznan.lab.engsw.carometro.faculdade.FaculdadeDto;

import java.util.Date;
import java.util.List;

public record CursoDto(
        Long id,
        List<AlunoDto> alunos,
        FaculdadeDto faculdade,
        String titulo,
        Date inauguracao
) {
    public static CursoDto toDto(Curso curso) {
        return new CursoDto(
                curso.getId(),
                curso.getAlunos().stream().map(AlunoDto::toDto).toList(),
                FaculdadeDto.toDto(curso.getFaculdade(), false),
                curso.getTitulo(),
                curso.getInauguracao()
        );
    }
    public static CursoDto toDto(Curso curso, boolean complete) {
        if(complete) {
            return toDto(curso);
        }
        return new CursoDto(
                curso.getId(),
                null,
                FaculdadeDto.toDto(curso.getFaculdade(), false),
                curso.getTitulo(),
                curso.getInauguracao()
        );
    }
}
