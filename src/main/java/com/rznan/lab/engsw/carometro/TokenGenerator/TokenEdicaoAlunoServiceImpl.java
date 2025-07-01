package com.rznan.lab.engsw.carometro.TokenGenerator;

import com.rznan.lab.engsw.carometro.TokenGenerator.interfaces.TokenEdicaoAlunoRepository;
import com.rznan.lab.engsw.carometro.TokenGenerator.interfaces.TokenEdicaoAlunoService;
import com.rznan.lab.engsw.carometro.aluno.Aluno;
import com.rznan.lab.engsw.carometro.aluno.AlunoServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Qualifier("tokenEdicaoAlunoServiceImpl")
public class TokenEdicaoAlunoServiceImpl implements TokenEdicaoAlunoService {

    @Autowired
    private TokenEdicaoAlunoRepository repository;

    @Autowired
    @Lazy
    private AlunoServiceImpl alunoService;

    @Override
    public String gerarTokenEdicao(long idAluno) {

        int tempoValidade = 15;

        Aluno aluno = alunoService.getAlunoById(idAluno);
        String tokenUnico = UUID.randomUUID().toString();
        LocalDateTime validade = LocalDateTime.now().plusDays(tempoValidade);
        TokenEdicaoAluno token = new TokenEdicaoAluno(tokenUnico, validade, aluno);

        repository.save(token);
        return tokenUnico;
    }


    public boolean validarToken(String token) {
        return repository.findByToken(token)
                .stream().anyMatch(t -> !t.isValido());
    }

    @Override
    @Transactional
    public void marcarComoUsado(String token) {
        TokenEdicaoAluno tokenCadastroAluno = repository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido: " + token));

        tokenCadastroAluno.setUsado(true);
        repository.save(tokenCadastroAluno);
    }


    // (Opcional) para limpeza automática

    @Transactional
    public void removerTokensExpirados() {
        List<TokenEdicaoAluno> expirados = repository.findAll().stream()
                .filter(TokenEdicaoAluno::isValido)
                .toList();

        repository.deleteAll(expirados);
    }
}
