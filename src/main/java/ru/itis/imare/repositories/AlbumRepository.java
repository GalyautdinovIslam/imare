package ru.itis.imare.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.imare.models.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Page<Album> findAllByOwner_Username(String username, Pageable pageable);
}
