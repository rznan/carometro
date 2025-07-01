package com.rznan.lab.engsw.carometro.aluno.dtos;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public record UpdateAlunoDto(

        @NotNull(message = "O ID do aluno é obrigatório")
        long id,
        @NotNull(message = "Curso é obrigatório")
        long idCurso,

        @NotBlank(message = "O nome do aluno é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        String apelido,
        @NotNull(message = "O ano de formado é obrigatória")
        @PastOrPresent(message = "O ano de formado deve estar no passado ou ser hoje")
        Year anoFormado,
        String sobreMimProfissional,

        String sobreMimSocial,
        String comentarioFaculdade,
        String comentarioLivre,
        String imagemPerfil,
        List<String> fotos,
        @URL(message = "Url Invalida")
        String linkedin
) {

        public UpdateAlunoDto(Aluno a){
                this(
                        a.getId(),
                        a.getCurso().getId(),
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
