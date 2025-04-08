package com.cessadev.library_system.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CreateCopyBookDTO {

  @NotBlank(message = "El código del ejemplar es obligatorio")
  @Size(min = 5, max = 20, message = "El código debe tener entre 5 y 20 caracteres")
  @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "El código solo puede contener letras, números y guiones")
  private String code;

  @NotBlank(message = "El ID del libro es obligatorio")
  private Long bookId;
}
