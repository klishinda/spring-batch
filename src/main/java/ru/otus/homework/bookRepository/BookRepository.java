package ru.otus.homework.bookRepository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework.model.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findBookById(Long id);
    List<Book> findAll();
}
