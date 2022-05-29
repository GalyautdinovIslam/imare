package ru.itis.imare.controllers.ui;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String getMainPage(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication.getName());
        return "main";
    }
}
