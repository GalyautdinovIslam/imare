package ru.itis.imare.services;

import org.springframework.security.core.Authentication;
import ru.itis.imare.dto.requests.CommentRequest;
import ru.itis.imare.dto.requests.PhotoCardRequest;
import ru.itis.imare.dto.responses.CommentResponse;
import ru.itis.imare.dto.responses.PhotoCardResponse;

public interface PhotoCardService {
    PhotoCardResponse getPhoto(Long id);

    void deletePhoto(Long id, Authentication authentication);

    void addPhotoToAlbum(Long photoId, Long albumId, Authentication authentication);

    void deletePhotoToAlbum(Long photoId, Long albumId, Authentication authentication);

    void updatePhoto(Long id, PhotoCardRequest photoCardRequest, Authentication authentication);
}
