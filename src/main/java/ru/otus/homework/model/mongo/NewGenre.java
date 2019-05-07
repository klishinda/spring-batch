package ru.otus.homework.model.mongo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.homework.model.postgresql.Genre;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class NewGenre {
  private String name;

  private NewGenre(String name) {
    this.name = name;
  }

  public static NewGenre convertGenre(Genre genre) {
    return new NewGenre(genre.getName());
  }

}