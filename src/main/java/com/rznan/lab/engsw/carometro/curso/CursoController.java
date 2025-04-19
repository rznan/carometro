package com.rznan.lab.engsw.carometro.curso;

import com.rznan.lab.engsw.carometro.aluno.IAlunoService;
import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.CreateCursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.CursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.DetailsCursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.UpdateCursoDto;
import com.rznan.lab.engsw.carometro.faculdade.IFaculdadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Lazy @Autowired
    private ICursoService cursoServiceImpl;

    @Lazy @Autowired
    private IAlunoService alunoServiceImpl;

    @Lazy @Autowired
    private IFaculdadeService faculdadeServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);

    @GetMapping
    public String loadListing(Model model) {
        logger.info("[Carômetro] -- Carregando página de Listagem de cursos");
        model.addAttribute("cursos", cursoServiceImpl.getAll());
        return  "curso/listing";
    }

    @GetMapping("/novo")
    public String loadNewCursoPage(Model model) {
        model.addAttribute("curso", new CreateCursoDto(List.of(), 0L, "", LocalDate.now()));
        model.addAttribute("todasFaculdades", faculdadeServiceImpl.getAll());
        model.addAttribute("todosAlunos", alunoServiceImpl.getAll());
        return "curso/registry";
    }

    @GetMapping("{id}/editar")
    public String loadEditCursoPage(@PathVariable Long id, Model model) {
        DetailsCursoDto tempDto = cursoServiceImpl.getById(id);
        System.out.println(tempDto);
        if (tempDto == null) {
            return "redirect:/curso";
        }
        UpdateCursoDto dto = new UpdateCursoDto(
                tempDto.id(),
                tempDto.alunos().stream().map(AlunoDto::idCurso).toList(),
                tempDto.faculdade() != null ? tempDto.faculdade().id() : -1,
                tempDto.titulo(),
                tempDto.inauguracao());
        model.addAttribute("curso", dto);
        model.addAttribute("todasFaculdades", faculdadeServiceImpl.getAll());
        model.addAttribute("todosAlunos", alunoServiceImpl.getAll());
        return "curso/update";
    }

    @PostMapping
    public String saveCurso(@Valid @ModelAttribute("curso") CreateCursoDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("todasFaculdades", faculdadeServiceImpl.getAll());
            model.addAttribute("todosAlunos", alunoServiceImpl.getAll());
            return "curso/registry";
        }
        try {
            CursoDto saved = cursoServiceImpl.save(dto);
        } catch (Exception e) {
            logger.error("Erro ao salvar curso", e);
        }
        return "redirect:/cursos";
    }

    @PutMapping
    public String updateCurso(@Valid @ModelAttribute("curso") UpdateCursoDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("todasFaculdades", faculdadeServiceImpl.getAll());
            model.addAttribute("todosAlunos", alunoServiceImpl.getAll());
            return "curso/update";
        }
        try {
            cursoServiceImpl.update(dto);
        } catch (Exception e) {
            logger.error("Erro ao atualizar curso", e);
        }
        return "redirect:/cursos";
    }

    @DeleteMapping
    public String deleteCurso(Long id) {
        cursoServiceImpl.delete(id);
        return "redirect:/cursos";
    }
}
