package com.rznan.lab.engsw.carometro.aluno.dtos;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.curso.dtos.CursoDto;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public record DetailsAlunoDto(

        long id,
        CursoDto curso,
        String nome,
        String apelido,
        Year anoFormado,
        String sobreMimProfissional,

        String sobreMimSocial,
        String comentarioFaculdade,
        String comentarioLivre,
        String imagemPerfil,
        List<String>fotos,
        String linkedin
) {
        public DetailsAlunoDto(Aluno a) {
                this(
                        a.getId(),
                        a.getCurso() != null ? new CursoDto(a.getCurso()) : null,
                        a.getNome(),
                        a.getApelido(),
                        a.getAnoFormado(),
                        a.getSobreMimProfissional(),
                        a.getSobreMimSocial(),
                        a.getComentarioFaculdade(),
                        a.getComentarioLivre(),
                        a.getImagemPerfil(),
                        a.getFotos(),
                        a.getLinkedin()
                );
        }
}
