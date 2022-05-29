package ru.itis.imare.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.imare.models.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByOwner_Username(String username, Pageable pageable);
    @Query(value = "select post from Post post where post.owner = " +
            "(select account from Account  account where account.username = :username) and post.id = :id")
    Optional<Post> findOwningById(Long id, String username);
}
