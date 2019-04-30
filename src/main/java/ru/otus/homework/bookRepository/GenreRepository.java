package ru.otus.homework.bookRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.postgresql.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
