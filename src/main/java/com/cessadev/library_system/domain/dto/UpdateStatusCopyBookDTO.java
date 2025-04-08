package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.enums.ECopyBookStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStatusCopyBookDTO {

  @NotNull(message = "El estado del ejemplar es requerido")
  private ECopyBookStatus status;
}
