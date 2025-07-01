package com.rznan.lab.engsw.carometro.TokenGenerator;

import com.rznan.lab.engsw.carometro.TokenGenerator.interfaces.TokenCadastroAlunoService;
import com.rznan.lab.engsw.carometro.TokenGenerator.interfaces.TokenEdicaoAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/adm")
public class TokenController {

    @Value("${app.url.base}")
    private String baseUrl;

    @Autowired
    private TokenCadastroAlunoService tokenCadastroService;

    @Autowired
    private TokenEdicaoAlunoService tokenEdicaoService;

    @GetMapping("/gerar")
    public String loadForm(Model model) {
        model.addAttribute("alunoId", 1);
        model.addAttribute("quantidade", 0);
        return "admin/tokengenerator";
    }

    // Token para criação em massa
    @PostMapping("/gerar-novos")
    public String gerarTokenCriacao(@RequestParam("quantidade") int quantidade, Model model) {
        List<String> tokensGerados = tokenCadastroService.gerarTokensNovos(quantidade);

        List<String> links = tokensGerados.stream()
                .map(token -> baseUrl + "/solicitacoes/novo?token=" + token)
                .toList();

        model.addAttribute("links", links);
        return "admin/tokengenerator";
    }

    // Token para edição de perfil de aluno
    @PostMapping("/gerar-edicao")
    public String gerarTokenEdicao(@RequestParam("alunoId") Long alunoId, Model model) {
        String token = tokenEdicaoService.gerarTokenEdicao(alunoId);

        // Inclui o ID do aluno no path e o token como query param
        String link = baseUrl + "/alunos/" + alunoId + "/editar?token=" + token;

        model.addAttribute("links", Collections.singletonList(link));
        return "admin/tokengenerator";
    }

}
