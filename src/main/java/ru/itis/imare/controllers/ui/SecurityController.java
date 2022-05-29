package ru.itis.imare.controllers.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "signIn";
    }
}
