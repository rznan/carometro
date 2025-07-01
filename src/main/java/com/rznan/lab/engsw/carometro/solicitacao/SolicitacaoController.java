package com.rznan.lab.engsw.carometro.solicitacao;

import com.rznan.lab.engsw.carometro.TokenGenerator.interfaces.TokenAlunoService;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import com.rznan.lab.engsw.carometro.curso.ICursoService;
import com.rznan.lab.engsw.carometro.solicitacao.dtos.SolicitacaoAlunoDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    @Autowired
    ISolicitacaoService solicitacaoService;

    @Autowired
    @Qualifier("tokenCadastroAlunoServiceImpl")
    TokenAlunoService tokenAlunoService;

    @Autowired
    ICursoService cursoService;
    private static final Logger logger = LoggerFactory.getLogger(SolicitacaoController.class);


    @GetMapping("/novo")
    public String loadFormPage(@RequestParam("token") String token, Model model) {
        if (!tokenAlunoService.validarToken(token)) {
            return "erro/token-invalido";
        }

        CreateAlunoDto dto = new CreateAlunoDto(
                0L, "", "", Year.now(), "", "", "", "", "", Collections.singletonList(""), ""
        );

        model.addAttribute("aluno", dto);
        model.addAttribute("cursos", cursoService.getAll());

        return "aluno/registry";
    }

    @PostMapping("/enviar")
    public String enviarSolicitacao(@Valid @ModelAttribute("aluno") CreateAlunoDto createAlunoDto,
                                    @RequestParam("imagem") MultipartFile imagem,
                                    @RequestParam("token") String token,
                                    Model model) {


        try {
            solicitacaoService.criarSolicitacao(createAlunoDto, imagem, token);
            tokenAlunoService.marcarComoUsado(token);
            model.addAttribute("mensagem", "Solicitação enviada com sucesso!");
            return "redirect:/alunos";
        } catch (Exception e) {
            logger.error("Erro ao enviar solicitação de cadastro", e);
            model.addAttribute("mensagem", "Erro ao enviar solicitação.");
            return "erro/erro_envio";
        }
    }

    @GetMapping("/pendentes")
    public String listarPendentes(Model model) {
        List<SolicitacaoAlunoDto> pendentes = solicitacaoService.listarSolicitacoesPendentes();
        model.addAttribute("solicitacoes", pendentes);
        return "admin/solicitacoes-pendentes";
    }

    @PostMapping("/{id}/aprovar")
    public String aprovar(@PathVariable Long id) {
        try {
            solicitacaoService.aprovarSolicitacao(id);
        } catch (Exception e) {
            logger.error("Erro ao enviar solicitação de cadastro", e);
            return "erro/erro_envio";
        }
        return "redirect:/solicitacoes/pendentes";
    }

    @PostMapping("/{id}/recusar")
    public String recusar(@PathVariable Long id) {
        try {
            solicitacaoService.rejeitarSolicitacao(id);
        } catch (Exception e) {
            logger.error("Erro ao enviar solicitação de cadastro", e);
            return "erro/erro_envio";
        }
        return "redirect:/solicitacoes/pendentes";
    }

    @GetMapping("/aprovadas")
    public String listarAprovadas(Model model) {
        List<SolicitacaoAlunoDto> aprovadas = solicitacaoService.listarSolicitacoesAprovadas();
        model.addAttribute("solicitacoes", aprovadas);
        return "admin/solicitacoes-aprovadas";
    }

    @GetMapping("/recusadas")
    public String listarRecusadas(Model model) {
        List<SolicitacaoAlunoDto> recusadas = solicitacaoService.listarSolicitacoesRecusadas();
        model.addAttribute("solicitacoes", recusadas);
        return "admin/solicitacoes-recusadas";
    }


}
