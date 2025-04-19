package com.rznan.lab.engsw.carometro.curso;

import com.rznan.lab.engsw.carometro.aluno.IAlunoService;
import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.CreateCursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.CursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.DetailsCursoDto;
import com.rznan.lab.engsw.carometro.curso.dtos.UpdateCursoDto;
import com.rznan.lab.engsw.carometro.faculdade.IFaculdadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String loadListing(Model model) {
        System.out.println("[Carômetro] -- Carregando página de Listagem de cursos");
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
                tempDto.faculdade().id(),
                tempDto.titulo(),
                tempDto.inauguracao());
        model.addAttribute("curso", dto);
        model.addAttribute("todasFaculdades", faculdadeServiceImpl.getAll());
        model.addAttribute("todosAlunos", alunoServiceImpl.getAll());
        return "curso/update";
    }

    @PostMapping
    public String saveCurso(@ModelAttribute CreateCursoDto dto) {
        try {
            CursoDto saved = cursoServiceImpl.save(dto);
        } catch (Exception e) {
            System.err.println("Não foi possível salvar o curso:\n\t" + dto);
            System.err.println(e.getMessage());
        }
        return "redirect:/cursos";
    }

    @PutMapping
    public String updateCurso(@ModelAttribute UpdateCursoDto dto) {
        try {
            cursoServiceImpl.update(dto);
        } catch (Exception e) {
            System.err.println("Não foi possível atualizar o curso:\n\t" + dto);
            System.err.println(e.getMessage());
        }
        return "redirect:/cursos";
    }

    @DeleteMapping
    public String deleteCurso(Long id) {
        cursoServiceImpl.delete(id);
        return "redirect:/cursos";
    }
}
