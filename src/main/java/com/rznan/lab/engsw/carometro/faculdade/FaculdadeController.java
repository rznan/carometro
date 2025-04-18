package com.rznan.lab.engsw.carometro.faculdade;

import com.rznan.lab.engsw.carometro.curso.ICursoService;
import com.rznan.lab.engsw.carometro.faculdade.dtos.CreateFaculdadeDto;
import com.rznan.lab.engsw.carometro.faculdade.dtos.DetailsFaculdadeDto;
import com.rznan.lab.engsw.carometro.faculdade.dtos.FaculdadeDto;
import com.rznan.lab.engsw.carometro.faculdade.dtos.UpdateFaculdadeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}/editar")
    public String loadEditFaculdadePage(@PathVariable Long id,  Model model) {
        DetailsFaculdadeDto dto = faculdadeServiceImpl.getById(id);
        if (dto == null) {
            return "redirect:faculdades";
        }
        model.addAttribute("faculdade", dto);
        model.addAttribute("cursos", cursoServiceImpl.getAll());
        return "faculdade/update";
    }

    @PostMapping
    public String saveFaculdade(@ModelAttribute CreateFaculdadeDto dto) {
        System.out.println("[Carômetro] -- Salvando nova Faculdade");
        FaculdadeDto saved = faculdadeServiceImpl.save(dto);
        System.out.println("[Carômetro] -- Salvo como: \n\t" + saved);
        return "redirect:faculdades";
    }

    @PutMapping
    public String updateFaculdade(@ModelAttribute UpdateFaculdadeDto dto) {
        faculdadeServiceImpl.update(dto);
        return "redirect:faculdades";
    }

    @DeleteMapping
    public String deleteFaculdade(Long id) {
        faculdadeServiceImpl.delete(id);
        return "redirect:faculdades";
    }

}
