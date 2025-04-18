package com.rznan.lab.engsw.carometro.faculdade;

import com.rznan.lab.engsw.carometro.curso.ICursoService;
import com.rznan.lab.engsw.carometro.faculdade.dtos.CreateFaculdadeDto;
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
@RequestMapping("/faculdades")
public class FaculdadeController {
    @Lazy @Autowired
    private IFaculdadeService faculdadeServiceImpl;
    @Lazy @Autowired
    private ICursoService cursoServiceImpl;

    @GetMapping
    public String loadListing(Model model) {
        System.out.println("[Carômetro] -- Carregando página listagem de faculdade");
        model.addAttribute("faculdades", faculdadeServiceImpl.getAll());
        return "faculdade/listing";
    }

    @GetMapping("/novo")
    public String loadNewFaculdadePage(Model model) {
        System.out.println("[Carômetro] -- Carregando página de nova faculdade");
        model.addAttribute("faculdade", new CreateFaculdadeDto(List.of(), "", LocalDate.now()));
        model.addAttribute("cursos", cursoServiceImpl.getAll());

        return "faculdade/registry";
    }

    @PostMapping
    public String saveFaculdade(@ModelAttribute CreateFaculdadeDto dto) {
        faculdadeServiceImpl.save(dto);
        return "redirect:faculdades";
    }
}
