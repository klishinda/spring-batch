package ru.otus.homework.model.mongo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class NewAuthor {
  private String surname;
  private String name;

  public NewAuthor(String surname, String name) {
    this.surname = surname;
    this.name = name;
  }
}