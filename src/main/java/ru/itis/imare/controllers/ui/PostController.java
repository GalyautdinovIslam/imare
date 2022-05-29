package ru.itis.imare.controllers.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.imare.dto.requests.PhotoCardRequest;
import ru.itis.imare.dto.responses.PostResponse;
import ru.itis.imare.services.PostService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public String getPostPage(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication.getName());
        return "post";
    }

    @GetMapping("/add")
    public String getAddPostPage(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication.getName());
        return "add_post";
    }

    @PostMapping("/add")
    public String addPost(MultipartFile[] files,
                          Authentication authentication) {
        return "redirect:/posts/" + postService.addPost(files, authentication).getId();
    }
}
