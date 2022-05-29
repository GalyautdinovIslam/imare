package ru.itis.imare.controllers.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{username}")
    public String getAccountPage(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication.getName());
        return "account";
    }
}
