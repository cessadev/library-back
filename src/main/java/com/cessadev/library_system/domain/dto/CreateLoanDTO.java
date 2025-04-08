package com.cessadev.library_system.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class CreateLoanDTO {

  @NotNull(message = "La categoría es obligatoria")
  @Positive(message = "El ID de categoría debe ser positivo")
  private Long categoryId;

  @NotNull(message = "El libro es obligatorio")
  @Positive(message = "El ID de libro debe ser positivo")
  private Long bookId;

  @NotNull(message = "La copia es obligatoria")
  @Positive(message = "El ID de copia debe ser positivo")
  private Long copyId;

  @NotNull(message = "El estudiante es obligatorio")
  @Positive(message = "El ID de estudiante debe ser positivo")
  private Long studentId;

  @NotNull(message = "La duración es obligatoria")
  @Min(value = 1, message = "Mínimo 1 día de préstamo")
  @Max(value = 15, message = "Máximo 15 días de préstamo")
  private Integer loanDays;
}
