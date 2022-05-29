package ru.itis.imare.controllers.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.imare.dto.requests.CommentRequest;
import ru.itis.imare.services.CommentService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add/{photo-id}")
    public String addCommentToPhoto(@PathVariable("photo-id") Long id,
                                    CommentRequest commentRequest,
                                    Authentication authentication) {
        commentService.addComment(id, commentRequest, authentication);
        return "redirect:/photos/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteComment(@PathVariable("id") Long id,
                                Authentication authentication) {
        commentService.deleteComment(id, authentication);
        return "redirect:/photos/" + id;
    }
}
