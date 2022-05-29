package ru.itis.imare.controllers.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.imare.services.AlbumService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/{id}")
    public String getAlbumPage(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication.getName());
        return "album";
    }

    @GetMapping("/add")
    public String getAddAlbumPage(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication.getName());
        return "add_album";
    }

    @PostMapping("/add")
    public String addAlbum(String title, Authentication authentication) {
        return "redirect:/albums/" + albumService.createAlbum(title, authentication).getId();
    }
}
