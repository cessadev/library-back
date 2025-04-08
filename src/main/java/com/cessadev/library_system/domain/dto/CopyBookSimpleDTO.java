package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.CopyBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CopyBookSimpleDTO {
  private Long id;
  private String code;

  public static CopyBookSimpleDTO fromEntity(CopyBook copy) {
    if (copy == null) {
      throw new IllegalArgumentException("CopyBook cannot be null");
    }

    CopyBookSimpleDTO dto = new CopyBookSimpleDTO();
    dto.setId(copy.getId());
    dto.setCode(copy.getCode() != null ? copy.getCode() : "N/A");
    return dto;
  }
}
