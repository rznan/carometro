package com.rznan.lab.engsw.carometro.solicitacao;

import com.rznan.lab.engsw.carometro.TokenGenerator.interfaces.TokenCadastroAlunoService;
import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.aluno.AlunoRepository;
import com.rznan.lab.engsw.carometro.aluno.IAlunoService;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import com.rznan.lab.engsw.carometro.solicitacao.dtos.SolicitacaoAlunoDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SolicitacaoServiceImpl implements ISolicitacaoService {

    @Autowired
    private IAlunoService alunoService;

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    SolicitacaoRepository repository;
    @Autowired
    private TokenCadastroAlunoService tokenCadastroAlunoService;

    @Override
    @Transactional
    public SolicitacaoAlunoDto criarSolicitacao(CreateAlunoDto dto, MultipartFile imagem, String token) throws Exception {

        if (!tokenCadastroAlunoService.validarToken(token)) {
            throw new IllegalArgumentException("Token inválido ou expirado.");
        }

        Aluno aluno = alunoService.save(dto, imagem);

        SolicitacaoAluno solicitacao = new SolicitacaoAluno();
        solicitacao.setAluno(aluno);
        tokenCadastroAlunoService.marcarComoUsado(token);
        return new SolicitacaoAlunoDto(repository.save(solicitacao));
    }

    @Override
    public List<SolicitacaoAlunoDto> listarSolicitacoesPendentes() {
        List<SolicitacaoAluno> lista =repository.findByStatus(StatusSolicitacao.PENDENTE);

        return lista.stream().map(SolicitacaoAlunoDto::new).toList();
    }

    @Override
    public List<SolicitacaoAlunoDto> listarSolicitacoesAprovadas() {
        List<SolicitacaoAluno> lista =repository.findByStatus(StatusSolicitacao.APROVADA);

        return lista.stream().map(SolicitacaoAlunoDto::new).toList();
    }

    @Override
    public List<SolicitacaoAlunoDto> listarSolicitacoesRecusadas() {
        List<SolicitacaoAluno> lista =repository.findByStatus(StatusSolicitacao.RECUSADA);

        return lista.stream().map(SolicitacaoAlunoDto::new).toList();
    }

    @Override
    public void aprovarSolicitacao(Long id) throws Exception {
        SolicitacaoAluno solicitacao = repository.findById(id)
                .orElseThrow(() -> new Exception("Solicitação não encontrada."));

        Aluno aluno = solicitacao.getAluno();
        aluno.setAprovado(true);
        alunoRepository.save(aluno);

        solicitacao.setStatus(StatusSolicitacao.APROVADA);
        repository.save(solicitacao);

    }

    @Override
    public void rejeitarSolicitacao(Long id) throws Exception {
        SolicitacaoAluno solicitacao = repository.findById(id)
                .orElseThrow(() -> new Exception("Solicitação não encontrada."));

        solicitacao.setStatus(StatusSolicitacao.RECUSADA);
        repository.save(solicitacao);

    }
}
