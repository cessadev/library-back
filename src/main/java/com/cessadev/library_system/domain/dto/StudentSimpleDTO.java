package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentSimpleDTO {
  private Long id;
  private String name;
  private String lastname;

  public static StudentSimpleDTO fromEntity(Student student) {
    StudentSimpleDTO dto = new StudentSimpleDTO();
    dto.setId(student.getId());
    dto.setName(student.getName());
    dto.setLastname(student.getLastname());
    return dto;
  }
}
