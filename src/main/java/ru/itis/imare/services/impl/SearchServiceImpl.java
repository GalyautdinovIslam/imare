package ru.itis.imare.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.imare.dto.responses.AbbreviatedAccountResponse;
import ru.itis.imare.dto.responses.AccountsPageResponse;
import ru.itis.imare.models.Account;
import ru.itis.imare.repositories.AccountRepository;
import ru.itis.imare.services.SearchService;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final AccountRepository accountRepository;

    @Value("${imare.search-page-size}")
    private int defaultSearchPageSize;

    @Override
    public AccountsPageResponse search(String search, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultSearchPageSize);
        search = "%" + search + "%";
        Page<Account> accounts = accountRepository.findAllBySearch(search, pageRequest);
        return AccountsPageResponse.builder()
                .accounts(AbbreviatedAccountResponse.from(accounts.getContent()))
                .totalPage(accounts.getTotalPages())
                .build();
    }
}
