package com.rznan.lab.engsw.carometro.aluno;

import com.rznan.lab.engsw.carometro.aluno.dtos.AlunoDto;
import com.rznan.lab.engsw.carometro.aluno.dtos.CreateAlunoDto;
import com.rznan.lab.engsw.carometro.curso.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
    @Lazy @Autowired
    IAlunoService alunoServiceImpl;
    @Lazy @Autowired
    private ICursoService cursoServiceImpl;

    @GetMapping
    public String loadListing(Model model) {
        System.out.println("[Carômetro] -- Carregando página listagem de aluno");
        model.addAttribute("alunos", alunoServiceImpl.getAll());
        return "aluno/listing";
    }

    @GetMapping("/novo")
    public String loadNewAlunoPage(Model model) {
        System.out.println("[Carômetro] -- Carregando página de nova aluno");
        model.addAttribute("aluno", new CreateAlunoDto( 0L,"","",LocalDate.now(),"","",""));
        model.addAttribute("cursos", cursoServiceImpl.getAll());

        return "aluno/registry";
    }

    @PostMapping
    public String saveAluno(@ModelAttribute CreateAlunoDto dto) throws Exception {
        AlunoDto saved = alunoServiceImpl.save(dto);
        return "redirect:/alunos";
    }








    
}
