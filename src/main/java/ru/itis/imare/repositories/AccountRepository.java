package ru.itis.imare.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.imare.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    @Query(value = "select account from Account account where account.username like :search or account.name like :search")
    Page<Account> findAllBySearch(String search, Pageable pageable);
}
