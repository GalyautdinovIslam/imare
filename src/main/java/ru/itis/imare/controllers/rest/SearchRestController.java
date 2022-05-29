package ru.itis.imare.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.imare.dto.responses.AccountsPageResponse;
import ru.itis.imare.services.SearchService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/search")
public class SearchRestController {

    private final SearchService searchService;

    @GetMapping("/account")
    public ResponseEntity<AccountsPageResponse> searchAccount(@RequestParam("search") String search,
                                                              @RequestParam("page") Integer page) {
        return ResponseEntity.ok(searchService.search(search, page));
    }
}
