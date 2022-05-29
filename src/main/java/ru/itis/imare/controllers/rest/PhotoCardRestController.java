package ru.itis.imare.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.imare.dto.responses.PhotoCardResponse;
import ru.itis.imare.services.PhotoCardService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/photos")
public class PhotoCardRestController {

    private final PhotoCardService photoCardService;

    @GetMapping("/{photo-id}")
    public ResponseEntity<PhotoCardResponse> getPhoto(@PathVariable("photo-id") Long id) {
        return ResponseEntity.ok(photoCardService.getPhoto(id));
    }

    @PostMapping("/{photo-id}/delete")
    public ResponseEntity<?> deletePhoto(@PathVariable("photo-id") Long id,
                                         Authentication authentication) {
        photoCardService.deletePhoto(id, authentication);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{photo-id}/add/{album-id}")
    public ResponseEntity<?> addPhotoToAlbum(@PathVariable("photo-id") Long photoId,
                                             @PathVariable("album-id") Long albumId,
                                             Authentication authentication) {
        photoCardService.addPhotoToAlbum(photoId, albumId, authentication);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{photo-id}/delete/{album-id}")
    public ResponseEntity<?> deletePhotoToAlbum(@PathVariable("photo-id") Long photoId,
                                             @PathVariable("album-id") Long albumId,
                                             Authentication authentication) {
        photoCardService.deletePhotoToAlbum(photoId, albumId, authentication);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
