package com.rznan.lab.engsw.carometro.faculdade.dtos;

import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.CursoDto;
import com.rznan.lab.engsw.carometro.faculdade.Faculdade;

import java.util.Date;
import java.util.List;

public record DetailsFaculdadeDto(
        Long id,
        List<CursoDto> cursos,
        String nome,
        Date inauguracao
) {

    public DetailsFaculdadeDto(Faculdade faculdade) {
            this(
                faculdade.getId(),
                faculdade.getCursos().stream().map(CursoDto::new).toList(),
                faculdade.getNome(),
                faculdade.getInauguracao()
        );
    }
}