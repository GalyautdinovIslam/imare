package ru.itis.imare.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.imare.dto.responses.AlbumsContentPageResponse;
import ru.itis.imare.dto.responses.TitleAlbumResponse;
import ru.itis.imare.services.AlbumService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {

    private final AlbumService albumService;

    @GetMapping("/{id}")
    public ResponseEntity<AlbumsContentPageResponse> getAlbum(@PathVariable("id") Long id,
                                                              @RequestParam("page") Integer page) {
        return ResponseEntity.ok(albumService.getAlbum(id, page));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> deleteAlbum(@PathVariable("id") Long id, Authentication authentication) {
        albumService.deleteAlbum(id, authentication);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
