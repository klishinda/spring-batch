package ru.otus.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int pages;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (
            name = "book_authors",
            joinColumns = @JoinColumn(name = "id_book", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_author", referencedColumnName = "id")
    )
    private Set<Author> authors;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (
            name = "genres_of_books",
            joinColumns = @JoinColumn(name = "id_book", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_genre", referencedColumnName = "id")
    )
    private Set<Genre> genres;
    @OneToMany(mappedBy = "idBook", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    public Book(String name, int pages, Set<Author> authors, Set<Genre> genres) {
        this.name = name;
        this.pages = pages;
        this.authors = authors;
        this.genres = genres;
    }
}

