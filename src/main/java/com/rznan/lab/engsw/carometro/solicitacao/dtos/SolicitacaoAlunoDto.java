package com.rznan.lab.engsw.carometro.solicitacao.dtos;

import com.rznan.lab.engsw.carometro.solicitacao.SolicitacaoAluno;

import java.time.LocalDateTime;

public record SolicitacaoAlunoDto(
        long id,
        LocalDateTime dataSolicitacao,
        long aluno_id,
        Enum statusSolicitacao
) {
    public SolicitacaoAlunoDto(SolicitacaoAluno a) {
        this(
                a.getId(),
                a.getDataSolicitacao(),
                a.getAluno().getId(),
                a.getStatus()
        );
    }


}
