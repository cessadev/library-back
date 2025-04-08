package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.Book;
import com.cessadev.library_system.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class CreateBookDTO {

  @NotBlank(message = "El título del libro es obligatorio")
  @Size(max = 255, message = "El título del libro no debe exceder los 255 caracteres")
  private String title;

  @NotBlank(message = "El autor del libro es obligatorio")
  @Size(max = 255, message = "El autor del libro no debe exceder los 255 caracteres")
  private String author;

  @NotBlank(message = "El ISBN del libro es obligatorio")
  @Size(max = 20, message = "El ISBN del libro no debe exceder los 20 caracteres")
  private String isbn;

  @Size(max = 1000, message = "La descripción del libro no debe exceder los 1000 caracteres")
  private String description;

  @NotNull(message = "El año de publicación es obligatorio")
  @Max(value = 2025, message = "El año de publicación debe ser vigente")
  private Integer yearPublication;

  @NotNull(message = "La categoría a la que pertenece el libro es obligatoria")
  private Long categoryId;

  public Book toEntity() {
    Book book = new Book();
    book.setTitle(this.title);
    book.setAuthor(this.author);
    book.setIsbn(this.isbn);
    book.setDescription(this.description);
    book.setYearPublication(String.valueOf(this.yearPublication));
    return book;
  }

  public Book toEntityWithCategory(Category category) {
    Book book = this.toEntity();
    book.setCategory(category);
    return book;
  }
}
