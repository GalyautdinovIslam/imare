package ru.itis.imare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.imare.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
