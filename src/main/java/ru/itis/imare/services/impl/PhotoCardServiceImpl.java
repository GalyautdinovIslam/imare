package ru.itis.imare.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.itis.imare.dto.requests.PhotoCardRequest;
import ru.itis.imare.dto.responses.PhotoCardResponse;
import ru.itis.imare.exceptions.notfound.AlbumNotFoundException;
import ru.itis.imare.exceptions.ForbiddenRequestException;
import ru.itis.imare.exceptions.notfound.PhotoCardNotFoundException;
import ru.itis.imare.models.Album;
import ru.itis.imare.models.PhotoCard;
import ru.itis.imare.models.Post;
import ru.itis.imare.repositories.AccountRepository;
import ru.itis.imare.repositories.AlbumRepository;
import ru.itis.imare.repositories.PhotoCardRepository;
import ru.itis.imare.repositories.PostRepository;
import ru.itis.imare.services.PhotoCardService;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PhotoCardServiceImpl implements PhotoCardService {

    private final PhotoCardRepository photoCardRepository;
    private final PostRepository postRepository;
    private final AlbumRepository albumRepository;
    private final AccountRepository accountRepository;

    @Override
    public PhotoCardResponse getPhoto(Long id) {
        return PhotoCardResponse.from(photoCardRepository.findById(id).orElseThrow(PhotoCardNotFoundException::new));
    }

    @Override
    public void deletePhoto(Long id, Authentication authentication) {
        PhotoCard photoCard = photoCardRepository.findById(id).orElseThrow(PhotoCardNotFoundException::new);
        Post post = photoCard.getPost();
        if (post.getOwner().getUsername().equals(authentication.getName())) {
            photoCard.getAlbums().forEach(x -> {
                x.getPhotoCards().remove(photoCard);
                albumRepository.save(x);
            });
            post.getPhotoCards().remove(photoCard);
            post = postRepository.save(post);
            photoCardRepository.delete(photoCard);
            if (post.getPhotoCards().size() == 1) postRepository.delete(post);
        } else {
            throw new ForbiddenRequestException();
        }
    }

    @Override
    public void addPhotoToAlbum(Long photoId, Long albumId, Authentication authentication) {
        PhotoCard photoCard = photoCardRepository.findById(photoId).orElseThrow(PhotoCardNotFoundException::new);
        Album album = albumRepository.findById(albumId).orElseThrow(AlbumNotFoundException::new);

        if (album.getOwner().getUsername().equals(authentication.getName())
                && !album.getType().equals(Album.Type.MAIN)) {
            album.getPhotoCards().add(photoCard);
            albumRepository.save(album);
        } else {
            throw new ForbiddenRequestException();
        }
    }

    @Override
    public void deletePhotoToAlbum(Long photoId, Long albumId, Authentication authentication) {
        PhotoCard photoCard = photoCardRepository.findById(photoId).orElseThrow(PhotoCardNotFoundException::new);
        Album album = albumRepository.findById(albumId).orElseThrow(AlbumNotFoundException::new);

        if (album.getOwner().getUsername().equals(authentication.getName())
                && !album.getType().equals(Album.Type.MAIN)) {
            album.getPhotoCards().remove(photoCard);
            albumRepository.save(album);
        } else {
            throw new ForbiddenRequestException();
        }
    }

    @Override
    public void updatePhoto(Long id, PhotoCardRequest photoCardRequest, Authentication authentication) {
        PhotoCard photoCard = photoCardRepository.findById(id).orElseThrow(PhotoCardNotFoundException::new);
        if (photoCard.getPost().getOwner().getUsername().equals(authentication.getName())) {
            photoCard.setLatitude(photoCardRequest.getLatitude());
            photoCard.setLongitude(photoCardRequest.getLongitude());
            photoCard.setTags(Arrays.stream(photoCardRequest.getUsernames().split(" "))
                    .map(x -> accountRepository.findByUsername(x).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
            photoCardRepository.save(photoCard);
        } else {
            throw new ForbiddenRequestException();
        }
    }
}
