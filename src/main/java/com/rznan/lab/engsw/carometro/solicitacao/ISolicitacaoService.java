package com.rznan.lab.engsw.carometro.solicitacao;

import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import com.rznan.lab.engsw.carometro.solicitacao.dtos.SolicitacaoAlunoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISolicitacaoService {
    SolicitacaoAlunoDto criarSolicitacao(CreateAlunoDto dto, MultipartFile imagem, String token) throws Exception;
    List<SolicitacaoAlunoDto> listarSolicitacoesPendentes();
    List<SolicitacaoAlunoDto> listarSolicitacoesAprovadas();
    List<SolicitacaoAlunoDto> listarSolicitacoesRecusadas();
    void aprovarSolicitacao(Long id) throws Exception;
    void rejeitarSolicitacao(Long id) throws Exception;
}
