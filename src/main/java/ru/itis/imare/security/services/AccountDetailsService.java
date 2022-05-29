package ru.itis.imare.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.imare.repositories.AccountRepository;
import ru.itis.imare.security.details.AccountDetails;

@RequiredArgsConstructor
@Service("customUserDetailsService")
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AccountDetails(accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found")));
    }
}
