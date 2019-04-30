package ru.otus.homework;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.bookRepository.BookRepository;
import ru.otus.homework.model.Book;

import java.util.*;


@ShellComponent
public class ShellCommands {
    private BookRepository bookRepository;

    public ShellCommands(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Books
    @ShellMethod("Get book by id")
    private Book getBookById(@ShellOption Long id) {
        return bookRepository.findBookById(id);
    }

    @ShellMethod("Get all books")
    private List<String> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<String> str = new ArrayList<>();
        for (Book b : books) {
            str.add(b.getName() + b.getPages());
        }
        return str;
    }
}
