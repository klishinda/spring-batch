package ru.otus.homework.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewComment {
  @Id
  private Long id;
  private byte mark;
  private String userName;
  private String comment;
  private Date createDate;

  public NewComment(byte mark, String userName, String comment, Date createDate) {
    this.mark = mark;
    this.userName = userName;
    this.comment = comment;
    this.createDate = createDate;
  }
}