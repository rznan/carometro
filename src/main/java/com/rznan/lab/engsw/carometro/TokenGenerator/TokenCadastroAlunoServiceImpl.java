package com.rznan.lab.engsw.carometro.TokenGenerator;

import com.rznan.lab.engsw.carometro.TokenGenerator.interfaces.TokenCadastroAlunoRepository;
import com.rznan.lab.engsw.carometro.TokenGenerator.interfaces.TokenCadastroAlunoService;
import com.rznan.lab.engsw.carometro.aluno.AlunoServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Qualifier("tokenCadastroAlunoServiceImpl")
public class TokenCadastroAlunoServiceImpl implements TokenCadastroAlunoService {

    @Autowired
    private TokenCadastroAlunoRepository repository;

    public List<String> gerarTokensNovos(int quantidade) {

        int tempoValidade = 30;

        List<TokenCadastroAluno> tokensParaSalvar = new ArrayList<>();
        List<String> tokensGerados = new ArrayList<>();

        LocalDateTime validade = LocalDateTime.now().plusDays(tempoValidade);

        for (int i = 0; i < quantidade; i++) {
            String tokenUnico = UUID.randomUUID().toString();
            TokenCadastroAluno token = new TokenCadastroAluno(tokenUnico, validade);

            tokensParaSalvar.add(token);
            tokensGerados.add(tokenUnico);
        }

        repository.saveAll(tokensParaSalvar);
        return tokensGerados;

    }


    public boolean validarToken(String token) {
        return repository.findByToken(token)
                .stream().anyMatch(t -> !t.isValido());
    }


    @Transactional
    public void marcarComoUsado(String token) {
        TokenCadastroAluno tokenCadastroAluno = repository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido: " + token));

        tokenCadastroAluno.setUsado(true);
        repository.save(tokenCadastroAluno);
    }

    // (Opcional) para limpeza automática
    @Transactional
    public void removerTokensExpirados() {
        List<TokenCadastroAluno> expirados = repository.findAll().stream()
                .filter(TokenCadastroAluno::isValido)
                .toList();

        repository.deleteAll(expirados);
    }
}
