package ru.itis.imare.services;

import ru.itis.imare.dto.responses.AccountsPageResponse;

public interface SearchService {
    AccountsPageResponse search(String search, Integer page);
}
