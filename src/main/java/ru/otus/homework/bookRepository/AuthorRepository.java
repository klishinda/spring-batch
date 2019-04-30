package ru.otus.homework.bookRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
