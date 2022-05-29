package ru.itis.imare.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.imare.dto.requests.PhotoCardRequest;
import ru.itis.imare.dto.responses.PostResponse;
import ru.itis.imare.services.PostService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id,
                                        Authentication authentication) {
        postService.deletePost(id, authentication);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
