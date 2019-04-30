package ru.otus.homework.bookRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
