package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.DetailsAlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.UpdateAlunoDto;
import com.rznan.lab.engsw.carometro.curso.CursoController;
import com.rznan.lab.engsw.carometro.curso.ICursoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.util.List;

import java.time.Year;
import java.util.Collections;


@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Lazy @Autowired
    IAlunoService alunoServiceImpl;

    @Lazy @Autowired
    private ICursoService cursoServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);


    @GetMapping
    public String loadListing(Model model) {
        model.addAttribute("alunos", alunoServiceImpl.findByStatus());
        return "aluno/listing";
    }
    @GetMapping("/buscar")
    @ResponseBody
    public List<AlunoDto> buscarAlunos(@RequestParam("nome") String nome) {
        return alunoServiceImpl.buscarPorNome(nome);
    }


    @GetMapping("/ver")
    public String loadVisualization(Model model) {
        model.addAttribute("alunos", alunoServiceImpl.findByStatus());
        model.addAttribute("cursos", cursoServiceImpl.getAll());
        return "aluno/visualization";
    }


    @GetMapping("/novo")
    public String loadNewAlunoPage(Model model) {
        model.addAttribute("aluno", new CreateAlunoDto( 0L,"","", Year.now(),"","","","","", Collections.singletonList(""),""));
        model.addAttribute("cursos", cursoServiceImpl.getAll());

        return "aluno/registry";
    }

    @PostMapping
    public String saveAluno(@Valid @ModelAttribute("aluno") CreateAlunoDto dto, BindingResult result, @RequestParam("imagem")  MultipartFile imagem, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("aluno", dto);
            model.addAttribute("cursos", cursoServiceImpl.getAll());
            return "aluno/registry"; // volta para a tela de cadastro com os erros
        }

        // Valida o tamanho da imagem (1MB = 1024 * 1024 bytes)
        if (!imagem.isEmpty() && imagem.getSize() > 1_048_576) {
            model.addAttribute("erroImagem", "A imagem não pode exceder 1MB.");
            model.addAttribute("aluno", dto);
            model.addAttribute("cursos", cursoServiceImpl.getAll());
            return "aluno/registry";
        }
        try {
            AlunoDto saved = alunoServiceImpl.save(dto, imagem);
            logger.info("[Carômetro] -- Aluno salvo como: {}", saved);
        }catch (Exception e){
            logger.error("Erro ao salvar curso", e);
        }
        return "redirect:/alunos";
    }



    @GetMapping("{id}/editar")
    public String loadEditFaculdadePage(@PathVariable Long id, Model model) {
        DetailsAlunoDto dto = alunoServiceImpl.getById(id);
        if (dto == null) {
            return "redirect:alunos";
        }
        UpdateAlunoDto nDto = new UpdateAlunoDto(
                dto.id(),
                dto.curso().id(),
                dto.nome(),
                dto.apelido(),
                dto.anoFormado(),
                dto.sobreMimProfissional(),
                dto.sobreMimSocial(),
                dto.comentarioFaculdade(),
                dto.comentarioLivre(),
                dto.imagemPerfil(),
                dto.fotos(),
                dto.linkedin()
        );
        model.addAttribute("aluno", nDto);
        model.addAttribute("cursos", cursoServiceImpl.getAll());
        return "aluno/update";
    }

    @PutMapping
    public String updateAluno(
            @Valid @ModelAttribute("aluno") UpdateAlunoDto dto,
            BindingResult result,
            Model model,
            @RequestParam(name = "imagem", required = false) MultipartFile imagem
    ) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("aluno", dto);
            model.addAttribute("cursos", cursoServiceImpl.getAll());
            return "aluno/update";
        }
        // Valida o tamanho da imagem (1MB = 1024 * 1024 bytes)
        if (!imagem.isEmpty() && imagem.getSize() > 1_048_576) {
            model.addAttribute("erroImagem", "A imagem não pode exceder 1MB.");
            model.addAttribute("aluno", dto);
            model.addAttribute("cursos", cursoServiceImpl.getAll());
            return "aluno/registry";
        }

        alunoServiceImpl.update(dto,imagem);
        return "redirect:/alunos";
    }

    @DeleteMapping
    public String deleteAluno(Long id) {
        alunoServiceImpl.delete(id);
        return "redirect:/alunos";
    }


    @GetMapping("{id}/detalhes")
    public String loadDetalhesAlunoPage(@PathVariable Long id, Model model) {
        DetailsAlunoDto dto = alunoServiceImpl.getById(id);
        if (dto == null) {
            return "redirect:alunos";
        }
        model.addAttribute("aluno", dto);
        return "aluno/details";
    }

    @GetMapping("/filtrar")
    public String filtrarAlunos(@RequestParam(required = false) Long curso,
                                @RequestParam(required = false) Integer ano,
                                @RequestParam(required = false) String nome,
                                Model model) {
        List<AlunoDto> alunosFiltrados = alunoServiceImpl.filter(curso, ano, nome);
        model.addAttribute("alunos", alunosFiltrados);
        model.addAttribute("cursos", cursoServiceImpl.getAll());

        // manter preenchimento
        model.addAttribute("cursoSelecionado", curso);
        model.addAttribute("anoSelecionado", ano);
        model.addAttribute("nomeSelecionado", nome);

        return "aluno/visualization";
    }

}
