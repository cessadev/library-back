package com.cessadev.library_system.domain;

import com.cessadev.library_system.domain.enums.ECourseStudent;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
  private Long id;
  private String name;
  private String lastname;
  private String identification;
  private String email;
  private ECourseStudent course;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(lastname, student.lastname) && Objects.equals(identification, student.identification) && Objects.equals(email, student.email) && course == student.course;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname, identification, email, course);
  }
}
