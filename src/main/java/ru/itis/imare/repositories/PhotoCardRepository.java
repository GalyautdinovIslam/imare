package ru.itis.imare.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.imare.models.Album;
import ru.itis.imare.models.PhotoCard;

import java.util.List;

public interface PhotoCardRepository extends JpaRepository<PhotoCard, Long> {
    Page<PhotoCard> findAllByAlbumsContains(Album album, Pageable pageable);
}
