package com.cessadev.library_system.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UpdateBookByIdDTO {

  @NotBlank(message = "El título del libro es obligatorio")
  @Size(max = 255, message = "El título del libro no debe exceder los 255 caracteres")
  private String title;

  @NotBlank(message = "El autor del libro es obligatorio")
  @Size(max = 255, message = "El autor del libro no debe exceder los 255 caracteres")
  private String author;

  @Size(max = 1000, message = "La descripción del libro no debe exceder los 1000 caracteres")
  private String description;

  @Max(value = 2025, message = "El año de publicación debe ser vigente")
  private Integer yearPublication;

  private Long categoryId;
}
