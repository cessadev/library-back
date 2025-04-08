package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.enums.ECourseStudent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStudentDTO {

  @NotBlank(message = "El nombre es obligatorio")
  @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
  @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
  private String name;

  @NotBlank(message = "El apellido es obligatorio")
  @Size(min = 2, max = 150, message = "El apellido debe tener entre 2 y 150 caracteres")
  @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo puede contener letras y espacios")
  private String lastname;

  @NotBlank(message = "El email es obligatorio")
  @Email(message = "Debe ser un email válido")
  @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
  private String email;

  @NotNull(message = "El curso es obligatorio")
  private ECourseStudent course;
}
