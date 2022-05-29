package ru.itis.imare.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.imare.converters.PostConverter;
import ru.itis.imare.dto.responses.PostResponse;
import ru.itis.imare.exceptions.notfound.AccountNotFoundException;
import ru.itis.imare.exceptions.ForbiddenRequestException;
import ru.itis.imare.exceptions.notfound.PostNotFoundException;
import ru.itis.imare.models.*;
import ru.itis.imare.repositories.*;
import ru.itis.imare.services.PostService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final AccountRepository accountRepository;
    private final PostRepository postRepository;
    private final FileInfoRepository fileInfoRepository;
    private final PhotoCardRepository photoCardRepository;
    private final AlbumRepository albumRepository;

    private final PostConverter postConverter;

    @Value("${imare.files-storage-path}")
    private String storagePath;

    @Override
    public PostResponse getPost(Long id) {
        return postConverter.convert(postRepository.findById(id).orElseThrow(PostNotFoundException::new));
    }

    @Override
    public PostResponse addPost(MultipartFile[] files, Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName())
                .orElseThrow(AccountNotFoundException::new);
        Post post = Post.builder()
                .owner(account)
                .build();
        post = postRepository.save(post);

        List<PhotoCard> photoCards = new ArrayList<>();
        for (MultipartFile file : files) {
            String extension = Objects.requireNonNull(file.getOriginalFilename())
                    .substring(file.getOriginalFilename().lastIndexOf("."));
            String storageFileName = UUID.randomUUID() + extension;

            FileInfo fileInfo = FileInfo.builder()
                    .storageFileName(storageFileName)
                    .originalFileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .build();
            try {
                Files.copy(file.getInputStream(), Paths.get(storagePath, fileInfo.getStorageFileName()));
                fileInfo = fileInfoRepository.save(fileInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            PhotoCard photoCard = PhotoCard.builder()
                    .post(post)
                    .photo(fileInfo)
                    .build();
            photoCard = photoCardRepository.save(photoCard);
            photoCards.add(photoCard);
        }

        Album main = account.getAlbums().stream()
                .filter(x -> x.getType().equals(Album.Type.MAIN))
                .findFirst()
                .get();

        post.setPhotoCards(photoCards);
        main.getPhotoCards().addAll(photoCards);
        albumRepository.save(main);
        return postConverter.convert(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id, Authentication authentication) {
        Post post = postRepository.findOwningById(id, authentication.getName()).orElseThrow(PostNotFoundException::new);
        post.getPhotoCards().forEach(x -> {
            List<Album> albums = x.getAlbums();
            for (Album album : albums) {
                album.getPhotoCards().remove(x);
                albumRepository.save(album);
            }
        });
        photoCardRepository.deleteAll(post.getPhotoCards());
        postRepository.delete(post);
    }
}
