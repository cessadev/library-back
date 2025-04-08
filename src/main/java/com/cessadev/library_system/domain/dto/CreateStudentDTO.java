package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.enums.ECourseStudent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class CreateStudentDTO {

  @NotBlank(message = "El nombre es obligatorio")
  @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
  @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
  private String name;

  @NotBlank(message = "El apellido es obligatorio")
  @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
  @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo puede contener letras y espacios")
  private String lastname;

  @NotBlank(message = "La identificación es obligatoria")
  @Size(min = 5, max = 20, message = "La identificación debe tener entre 5 y 20 caracteres")
  @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "La identificación solo puede contener letras, números y guiones")
  private String identification;

  @NotBlank(message = "El email es obligatorio")
  @Email(message = "Debe ser un email válido")
  @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
  private String email;

  @NotNull(message = "El curso es obligatorio")
  private ECourseStudent course;
}
