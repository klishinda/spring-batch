package ru.otus.homework.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "library")
public class NewBook {
  @Id
  ObjectId databaseId;
  private String name;
  private int pages;
  private Set<NewAuthor> authors;
  private Set<NewGenre> genres;
  private Set<NewComment> comments;

  public NewBook(String name, int pages) {
    this.name = name;
    this.pages = pages;
  }

  public NewBook(String name, int pages, Set<NewAuthor> authors, Set<NewGenre> genres) {
    this.name = name;
    this.pages = pages;
    this.authors = authors;
    this.genres = genres;
  }
}