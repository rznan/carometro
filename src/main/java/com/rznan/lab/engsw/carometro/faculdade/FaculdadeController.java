package com.rznan.lab.engsw.carometro.faculdade;

import com.rznan.lab.engsw.carometro.faculdade.dtos.FaculdadeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/faculdades")
public class FaculdadeController {
    @Lazy @Autowired
    private IFaculdadeService faculdadeServiceImpl;

    @GetMapping
    public String loadListing(Model model) {
        List<FaculdadeDto> faculdadeDtos = stubGetAllFaculdades();
        model.addAttribute("faculdades", faculdadeDtos);
        return "faculdade/listing";
    }

    private static List<FaculdadeDto> stubGetAllFaculdades() {
        Faculdade f = new Faculdade(1, null, "Teste", Date.from(Instant.now()));
        List<FaculdadeDto> fs = new ArrayList<>();
        fs.add(new FaculdadeDto(f));
        return fs;
    }


}
