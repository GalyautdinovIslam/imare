package ru.itis.imare.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.itis.imare.dto.responses.AlbumsContentPageResponse;
import ru.itis.imare.dto.responses.PhotoCardInAlbumResponse;
import ru.itis.imare.dto.responses.TitleAlbumResponse;
import ru.itis.imare.exceptions.ForbiddenRequestException;
import ru.itis.imare.exceptions.notfound.AccountNotFoundException;
import ru.itis.imare.exceptions.notfound.AlbumNotFoundException;
import ru.itis.imare.models.Account;
import ru.itis.imare.models.Album;
import ru.itis.imare.models.PhotoCard;
import ru.itis.imare.repositories.AccountRepository;
import ru.itis.imare.repositories.AlbumRepository;
import ru.itis.imare.repositories.PhotoCardRepository;
import ru.itis.imare.services.AlbumService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AlbumServiceImpl implements AlbumService {

    @Value("${imare.albums-page-size}")
    private int defaultAlbumsPageSize;

    private final AccountRepository accountRepository;
    private final AlbumRepository albumRepository;
    private final PhotoCardRepository photoCardRepository;

    @Override
    public AlbumsContentPageResponse getAlbum(Long id, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultAlbumsPageSize);
        Album album = albumRepository.findById(id).orElseThrow(AlbumNotFoundException::new);
        Page<PhotoCard> photoCards = photoCardRepository.findAllByAlbumsContains(album, pageRequest);
        return AlbumsContentPageResponse.builder()
                .owner(album.getOwner().getUsername())
                .title(album.getTitle())
                .photos(PhotoCardInAlbumResponse.from(photoCards.getContent()))
                .totalPage(photoCards.getTotalPages())
                .build();
    }

    @Override
    public TitleAlbumResponse createAlbum(String title, Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName())
                .orElseThrow(AccountNotFoundException::new);
        Album album = Album.builder()
                .owner(account)
                .title(title)
                .type(Album.Type.COMMON)
                .build();
        return TitleAlbumResponse.from(albumRepository.save(album));
    }

    @Override
    public void deleteAlbum(Long id, Authentication authentication) {
        Album album = albumRepository.findById(id).orElseThrow(AlbumNotFoundException::new);
        if (album.getOwner().getUsername().equals(authentication.getName())
                && !album.getType().equals(Album.Type.MAIN)) {
            album.setPhotoCards(new ArrayList<>());
            album = albumRepository.save(album);
            albumRepository.delete(album);
        } else {
            throw new ForbiddenRequestException();
        }
    }

    @Override
    public List<TitleAlbumResponse> getAlbumsList(String username) {
        return TitleAlbumResponse.from(accountRepository.findByUsername(username)
                .orElseThrow(AccountNotFoundException::new).getAlbums().stream()
                .filter(x -> !x.getType().equals(Album.Type.MAIN))
                .collect(Collectors.toList()));
    }
}
