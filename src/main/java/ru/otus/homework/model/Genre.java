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
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Book> books;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Long id) {
        this.id = id;
    }
}
