package com.cessadev.library_system.domain;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private String description;
  private String yearPublication;
  private Long categoryId;

  // Objeto completo para consultas específicas
  private Category category;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(id, book.id) &&
            Objects.equals(title, book.title) &&
            Objects.equals(author, book.author) &&
            Objects.equals(isbn, book.isbn) &&
            Objects.equals(description, book.description) &&
            Objects.equals(yearPublication, book.yearPublication);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, isbn, description, yearPublication);
  }
}
