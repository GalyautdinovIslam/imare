package ru.itis.imare.controllers.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SearchController {

    @GetMapping("/search")
    public String getSearchPage(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication.getName());
        return "search";
    }
}
