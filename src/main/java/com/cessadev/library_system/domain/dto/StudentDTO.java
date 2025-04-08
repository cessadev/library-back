package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.Student;
import com.cessadev.library_system.domain.enums.ECourseStudent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
  private Long id;
  private String name;
  private String lastname;
  private String identification;
  private String email;
  private ECourseStudent course;

  public static StudentDTO fromEntity(Student student) {
    StudentDTO dto = new StudentDTO();
    dto.setId(student.getId());
    dto.setName(student.getName());
    dto.setLastname(student.getLastname());
    dto.setIdentification(student.getIdentification());
    dto.setEmail(student.getEmail());
    dto.setCourse(student.getCourse());
    return dto;
  }
}
