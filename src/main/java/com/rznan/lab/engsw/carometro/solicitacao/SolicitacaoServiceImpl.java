package com.rznan.lab.engsw.carometro.solicitacao;

import java.util.List;

public class SolicitacaoServiceImpl implements ISolicitacaoService{
    @Override
    public SolicitacaoAluno criarSolicitacao(SolicitacaoAluno solicitacao) {
        return null;
    }

    @Override
    public List<SolicitacaoAluno> listarSolicitacoesPendentes() {
        return null;
    }

    @Override
    public List<SolicitacaoAluno> listarSolicitacoesAprovadas() {
        return null;
    }

    @Override
    public List<SolicitacaoAluno> listarSolicitacoesRecusadas() {
        return null;
    }

    @Override
    public SolicitacaoAluno buscarPorId(Long id) {
        return null;
    }

    @Override
    public void aprovarSolicitacao(Long id) {

    }

    @Override
    public void rejeitarSolicitacao(Long id, String motivo) {

    }
}
