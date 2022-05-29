package ru.itis.imare.services;

import org.springframework.security.core.Authentication;
import ru.itis.imare.dto.requests.SignUpRequest;
import ru.itis.imare.dto.responses.*;

public interface AccountService {
    AccountProfileResponse getAccountProfile(String username);

    AccountsPageResponse getAccountFollowers(String username, Integer page);

    AccountsPageResponse getAccountFollowings(String username, Integer page);

    PostsPageResponse getAccountPosts(String username, Integer page);

    void follow(String following, Authentication authentication);

    void unfollow(String following, Authentication authentication);

    UsernameAccountResponse createAccount(SignUpRequest signUpRequest);

    AlbumsPageResponse getAccountAlbums(String username, Integer page);
}
