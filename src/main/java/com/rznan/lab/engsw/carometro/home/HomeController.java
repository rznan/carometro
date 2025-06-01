package com.rznan.lab.engsw.carometro.home;

import com.rznan.lab.engsw.carometro.aluno.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @Autowired
    IAlunoService alunoServiceImpl;

    @RequestMapping("/")
    public String carregarHome(Model model) {
        model.addAttribute("alunos", alunoServiceImpl.getAll());
        return "home/home";
    }
}
