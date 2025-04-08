package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookWithCategoryDTO {
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private String description;
  private String yearPublication;
  private CategoryDTO category;

  public static BookWithCategoryDTO fromEntity(Book book) {
    BookWithCategoryDTO dto = new BookWithCategoryDTO();
    dto.setId(book.getId());
    dto.setTitle(book.getTitle());
    dto.setAuthor(book.getAuthor());
    dto.setIsbn(book.getIsbn());
    dto.setDescription(book.getDescription());
    dto.setYearPublication(book.getYearPublication());

    if (book.getCategory() != null) {
      dto.setCategory(new CategoryDTO(book.getCategory()));
    }

    return dto;
  }
}
