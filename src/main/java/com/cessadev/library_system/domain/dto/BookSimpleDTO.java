package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookSimpleDTO {
  private Long id;
  private String title;
  private String isbn;

  public static BookSimpleDTO fromEntity(Book book) {
    BookSimpleDTO dto = new BookSimpleDTO();
    dto.setId(book.getId());
    dto.setTitle(book.getTitle());
    dto.setIsbn(book.getIsbn());
    return dto;
  }
}
