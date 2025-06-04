package com.rznan.lab.engsw.carometro.TokenGenerator;

import com.rznan.lab.engsw.carometro.aluno.AlunoServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TokenCadastroAlunoImpl implements TokenCadastroAlunoService {

    @Autowired
    private TokenCadastroAlunoRepository repository;


    @Autowired
    @Lazy
    private AlunoServiceImpl alunoService;

    public List<String> gerarTokensNovos(int quantidade) {

        List<TokenCadastroAluno> tokensParaSalvar = new ArrayList<>();
        List<String> tokensGerados = new ArrayList<>();

        LocalDateTime validade = LocalDateTime.now().plusDays(30);

        for (int i = 0; i < quantidade; i++) {
            String tokenUnico = UUID.randomUUID().toString();
            TokenCadastroAluno token = new TokenCadastroAluno(tokenUnico, validade);

            tokensParaSalvar.add(token);
            tokensGerados.add(tokenUnico);
        }

        repository.saveAll(tokensParaSalvar);
        return tokensGerados;

    }

    @Override
    public TokenCadastroAluno validarToken(String token) {
        return repository.findByToken(token)
                .filter(t -> !t.isValido())
                .orElse(null);
    }


    @Override
        @Transactional
        public void marcarComoUsado (TokenCadastroAluno token){
            token.setUsado(true);
            repository.save(token);
        }

        // (Opcional) para limpeza automática
        @Override
        @Transactional
        public void removerTokensExpirados () {
            List<TokenCadastroAluno> expirados = repository.findAll().stream()
                    .filter(TokenCadastroAluno::isValido)
                    .toList();

            repository.deleteAll(expirados);
        }
    }
