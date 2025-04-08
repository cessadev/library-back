package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.CopyBook;
import com.cessadev.library_system.domain.enums.ECopyBookStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CopyBookDTO {
  private Long id;
  private String code;
  private ECopyBookStatus status;
  private BookSimpleDTO book;

  public static CopyBookDTO fromEntity(CopyBook copy) {
    CopyBookDTO dto = new CopyBookDTO();
    dto.setId(copy.getId());
    dto.setCode(copy.getCode());
    dto.setStatus(copy.getStatus());
    dto.setBook(BookSimpleDTO.fromEntity(copy.getBook()));
    return dto;
  }
}
