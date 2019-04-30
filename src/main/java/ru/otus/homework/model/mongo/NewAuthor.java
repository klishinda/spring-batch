package ru.otus.homework.model.mongo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.homework.model.postgresql.Author;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class NewAuthor {
  private String surname;
  private String name;

  private NewAuthor(String surname, String name) {
    this.surname = surname;
    this.name = name;
  }

  public static NewAuthor convertAuthor(Author author) {
    return new NewAuthor(author.getSurname(), author.getName());
  }
}