package com.rznan.lab.engsw.carometro.curso.dtos;

import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.curso.Curso;
import com.rznan.lab.engsw.carometro.faculdade.dtos.FaculdadeDto;

import java.time.LocalDate;
import java.util.List;

public record DetailsCursoDto(
        Long id,
        List<AlunoDto> alunos,
        FaculdadeDto faculdade,
        String titulo,
        LocalDate inauguracao
) {
    public DetailsCursoDto(Curso curso) {
            this(
                curso.getId(),
                curso.getAlunos().stream().map(AlunoDto::new).toList(),
                new FaculdadeDto(curso.getFaculdade()),
                curso.getTitulo(),
                curso.getInauguracao()
        );
    }
}