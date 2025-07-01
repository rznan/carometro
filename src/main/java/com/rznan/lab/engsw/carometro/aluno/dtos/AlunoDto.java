package com.rznan.lab.engsw.carometro.aluno.dtos;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public record AlunoDto(

        long id,

        long idCurso,
        String tituloCurso,

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

    public AlunoDto(Aluno a) {
        this(
                a.getId(),
                a.getCurso().getId(),
                a.getCurso().getTitulo(),
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
