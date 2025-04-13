package com.rznan.lab.engsw.carometro.aluno;

// TODO: usar dtos de curso e faculdade no lugar do id
public record AlunoDto(
        long id,
        long idCurso,
        long idFaculdade,
        String ra,
        String nome,
        int anoEntrada,
        String historico,
        String comentarioFaculdade,
        String comentarioLivre
) {
        // TODO: Construtor que preenche os dtos de curso e faculdade com dados básicos (p.ex: id e nome)
        // TODO: Construtor que preenche os dtos de curso e faculdade apenas com ID
        public AlunoDto(Aluno a) {
                this(
                        a.getId(),
                        a.getCurso().getId(),
                        a.getFaculdade().getId(),
                        a.getRa(),
                        a.getNome(),
                        a.getAnoEntrada(),
                        a.getHistorico(),
                        a.getComentarioFaculdade(),
                        a.getComentarioLivre()
                );
        }
}
