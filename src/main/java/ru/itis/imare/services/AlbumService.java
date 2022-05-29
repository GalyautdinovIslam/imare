package ru.itis.imare.services;

import org.springframework.security.core.Authentication;
import ru.itis.imare.dto.responses.AlbumsContentPageResponse;
import ru.itis.imare.dto.responses.TitleAlbumResponse;

import java.util.List;

public interface AlbumService {
    AlbumsContentPageResponse getAlbum(Long id, Integer page);

    TitleAlbumResponse createAlbum(String title, Authentication authentication);

    void deleteAlbum(Long id, Authentication authentication);

    List<TitleAlbumResponse> getAlbumsList(String username);
}
