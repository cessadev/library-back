package com.cessadev.library_system.domain;

import com.cessadev.library_system.domain.enums.ECopyBookStatus;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CopyBook {

  private Long id;
  private String code;
  private ECopyBookStatus status;
  private Long bookId;

  // Objeto completo para consultas específicas
  private Book book;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    CopyBook copyBook = (CopyBook) o;
    return Objects.equals(id, copyBook.id) &&
            Objects.equals(code, copyBook.code) &&
            status == copyBook.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, code, status);
  }
}
