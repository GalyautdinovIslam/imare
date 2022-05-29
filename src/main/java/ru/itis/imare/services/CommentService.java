package ru.itis.imare.services;

import org.springframework.security.core.Authentication;
import ru.itis.imare.dto.requests.CommentRequest;
import ru.itis.imare.dto.responses.CommentResponse;

public interface CommentService {

    CommentResponse addComment(Long id, CommentRequest commentRequest, Authentication authentication);

    void deleteComment(Long id, Authentication authentication);
}
