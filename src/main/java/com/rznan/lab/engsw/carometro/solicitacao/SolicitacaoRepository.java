package com.rznan.lab.engsw.carometro.solicitacao;

import com.rznan.lab.engsw.carometro.solicitacao.dtos.SolicitacaoAlunoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<SolicitacaoAluno,Long> {
    List<SolicitacaoAluno> findByStatus(StatusSolicitacao status);
}
