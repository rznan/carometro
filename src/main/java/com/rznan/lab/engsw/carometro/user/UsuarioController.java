package com.rznan.lab.engsw.carometro.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/adm")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("usuario", new Usuario());
        if (error != null) {
            model.addAttribute("msg", "Usuário ou senha inválidos");
        }
        return "login/adm";
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/adm");
    }
}
