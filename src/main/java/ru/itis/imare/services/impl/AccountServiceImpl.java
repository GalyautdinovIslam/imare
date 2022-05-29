package ru.itis.imare.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.imare.dto.requests.SignUpRequest;
import ru.itis.imare.dto.responses.*;
import ru.itis.imare.exceptions.notfound.AccountNotFoundException;
import ru.itis.imare.exceptions.ForbiddenRequestException;
import ru.itis.imare.exceptions.notfound.SubscriptionNotFoundException;
import ru.itis.imare.models.Account;
import ru.itis.imare.models.Album;
import ru.itis.imare.models.Post;
import ru.itis.imare.models.Subscription;
import ru.itis.imare.repositories.AccountRepository;
import ru.itis.imare.repositories.AlbumRepository;
import ru.itis.imare.repositories.PostRepository;
import ru.itis.imare.repositories.SubscriptionRepository;
import ru.itis.imare.services.AccountService;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    @Value("${imare.subscriptions-page-size}")
    private int defaultSubscriptionPageSize;
    @Value("${imare.posts-page-size}")
    private int defaultPostPageSize;

    private final AccountRepository accountRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PostRepository postRepository;
    private final AlbumRepository albumRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountProfileResponse getAccountProfile(String username) {
        return AccountProfileResponse.from(accountRepository.findByUsername(username)
                .orElseThrow(AccountNotFoundException::new));
    }

    @Override
    public AccountsPageResponse getAccountFollowers(String username, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultSubscriptionPageSize);
        Page<Subscription> subscriptions = subscriptionRepository.findAllByFollowing_Username(username, pageRequest);
        return AccountsPageResponse.builder()
                .accounts(
                        AbbreviatedAccountResponse.from(subscriptions.getContent().stream()
                                .map(Subscription::getFollower)
                                .collect(Collectors.toList()))
                )
                .totalPage(subscriptions.getTotalPages())
                .build();
    }

    @Override
    public AccountsPageResponse getAccountFollowings(String username, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultSubscriptionPageSize);
        Page<Subscription> subscriptions = subscriptionRepository.findAllByFollower_Username(username, pageRequest);
        return AccountsPageResponse.builder()
                .accounts(
                        AbbreviatedAccountResponse.from(subscriptions.getContent().stream()
                                .map(Subscription::getFollowing)
                                .collect(Collectors.toList()))
                )
                .totalPage(subscriptions.getTotalPages())
                .build();
    }

    @Override
    public PostsPageResponse getAccountPosts(String username, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPostPageSize);
        Page<Post> posts = postRepository.findAllByOwner_Username(username, pageRequest);
        return PostsPageResponse.builder()
                .posts(AbbreviatedPostResponse.from(posts.getContent()))
                .totalPage(posts.getTotalPages())
                .build();
    }

    @Override
    public void follow(String following, Authentication authentication) {
        System.out.println(authentication.getName());
        Account follower = accountRepository.findByUsername(authentication.getName())
                .orElseThrow(AccountNotFoundException::new);
        Account toFollow = accountRepository.findByUsername(following)
                .orElseThrow(AccountNotFoundException::new);
        if (follower.getUsername().equals(toFollow.getUsername()))
            throw new ForbiddenRequestException();
        Subscription subscription = Subscription.builder()
                .follower(follower)
                .following(toFollow)
                .build();
        log.info(follower.getUsername() + " follow to " + toFollow.getUsername());
        subscriptionRepository.save(subscription);
    }

    @Override
    public void unfollow(String following, Authentication authentication) {
        Subscription subscription = subscriptionRepository
                .findByFollower_UsernameAndFollowing_Username(authentication.getName(), following)
                .orElseThrow(SubscriptionNotFoundException::new);
        log.info(subscription.getFollower().getUsername() + " unfollow from " + subscription.getFollowing().getUsername());
        subscriptionRepository.delete(subscription);
    }

    @Override
    public UsernameAccountResponse createAccount(SignUpRequest signUpRequest) {
        Account account = Account.builder()
                .username(signUpRequest.getUsername().toLowerCase())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .information(signUpRequest.getInformation())
                .build();
        account = accountRepository.save(account);
        Album main = Album.builder()
                .owner(account)
                .title("MAIN")
                .type(Album.Type.MAIN)
                .build();
        albumRepository.save(main);
        return UsernameAccountResponse.from(account);
    }

    @Override
    public AlbumsPageResponse getAccountAlbums(String username, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPostPageSize);
        Page<Album> albums = albumRepository.findAllByOwner_Username(username, pageRequest);
        return AlbumsPageResponse.builder()
                .totalPage(albums.getTotalPages())
                .albums(AlbumResponse.from(albums.getContent()))
                .build();
    }
}
