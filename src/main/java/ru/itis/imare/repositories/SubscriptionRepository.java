package ru.itis.imare.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.imare.models.Subscription;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Page<Subscription> findAllByFollowing_Username(String username, Pageable pageable);

    Page<Subscription> findAllByFollower_Username(String username, Pageable pageable);

    Optional<Subscription> findByFollower_UsernameAndFollowing_Username(String follower, String following);
}
