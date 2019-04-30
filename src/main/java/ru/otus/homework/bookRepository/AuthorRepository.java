package ru.otus.homework.bookRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.postgresql.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
