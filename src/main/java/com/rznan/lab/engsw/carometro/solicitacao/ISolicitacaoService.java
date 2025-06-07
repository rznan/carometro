package com.rznan.lab.engsw.carometro.solicitacao;

import java.util.List;

public interface ISolicitacaoService {
    SolicitacaoAluno criarSolicitacao(SolicitacaoAluno solicitacao);
    List<SolicitacaoAluno> listarSolicitacoesPendentes();
    List<SolicitacaoAluno> listarSolicitacoesAprovadas();
    List<SolicitacaoAluno> listarSolicitacoesRecusadas();
    SolicitacaoAluno buscarPorId(Long id);
    void aprovarSolicitacao(Long id);
    void rejeitarSolicitacao(Long id, String motivo);
}
