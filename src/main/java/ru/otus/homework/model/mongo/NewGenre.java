package ru.otus.homework.model.mongo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class NewGenre {
  private String name;

  public NewGenre(String name) {
    this.name = name;
  }
}