package ru.itis.imare.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.imare.dto.requests.SignUpRequest;
import ru.itis.imare.dto.responses.*;
import ru.itis.imare.services.AccountService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping("/{username}")
    public ResponseEntity<AccountProfileResponse> getAccountProfile(@PathVariable("username") String username) {
        return ResponseEntity.ok(accountService.getAccountProfile(username));
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<AccountsPageResponse> getAccountFollowers(@PathVariable("username") String username,
                                                                    @RequestParam("page") Integer page) {
        return ResponseEntity.ok(accountService.getAccountFollowers(username, page));
    }

    @GetMapping("/{username}/followings")
    public ResponseEntity<AccountsPageResponse> getAccountFollowings(@PathVariable("username") String username,
                                                                     @RequestParam("page") Integer page) {
        return ResponseEntity.ok(accountService.getAccountFollowings(username, page));
    }

    @GetMapping("/{username}/posts")
    public ResponseEntity<PostsPageResponse> getAccountPosts(@PathVariable("username") String username,
                                                            @RequestParam("page") Integer page) {
        return ResponseEntity.ok(accountService.getAccountPosts(username, page));
    }

    @GetMapping("/{username}/albums")
    public ResponseEntity<AlbumsPageResponse> getAccountAlbums(@PathVariable("username") String username,
                                                               @RequestParam("page") Integer page) {
        return ResponseEntity.ok(accountService.getAccountAlbums(username, page));
    }

    @PostMapping("/{username}/follow")
    public ResponseEntity<?> follow(@PathVariable("username") String following,
                                    Authentication authentication) {
        accountService.follow(following, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{username}/unfollow")
    public ResponseEntity<?> unfollow(@PathVariable("username") String following,
                                      Authentication authentication) {
        accountService.unfollow(following, authentication);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/create")
        public ResponseEntity<UsernameAccountResponse> createAccount(@Valid @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccount(signUpRequest));
    }
}
