package ru.itis.imare.services;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.imare.dto.requests.PhotoCardRequest;
import ru.itis.imare.dto.responses.PostResponse;

public interface PostService {
    PostResponse getPost(Long id);

    PostResponse addPost(MultipartFile[] files, Authentication authentication);

    void deletePost(Long id, Authentication authentication);
}
