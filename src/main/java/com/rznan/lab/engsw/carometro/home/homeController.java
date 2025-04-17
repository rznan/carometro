package com.rznan.lab.engsw.carometro.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

    @RequestMapping("/")
    public String carregarHome(Model model) {
        return "home/home";
    }
}
