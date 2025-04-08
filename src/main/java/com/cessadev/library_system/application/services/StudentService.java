package com.cessadev.library_system.application.services;

import com.cessadev.library_system.application.ports.StudentRepository;
import com.cessadev.library_system.domain.Student;
import com.cessadev.library_system.domain.enums.ECourseStudent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Transactional(readOnly = true)
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Student getStudentById(Long id) {
    return studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
  }

  @Transactional(readOnly = true)
  public Student getStudentByIdentification(String identification) {
    return studentRepository.findByIdentification(identification)
            .orElseThrow(() -> new IllegalArgumentException("Student not found with identification: " + identification));
  }

  @Transactional
  public Student createStudent(String name, String lastname, String identification, String email, ECourseStudent course) {
    if (studentRepository.findByIdentification(identification).isPresent()) {
      throw new IllegalArgumentException("A student with this identification already exists");
    }

    Student student = new Student();
    student.setName(name);
    student.setLastname(lastname);
    student.setIdentification(identification);
    student.setEmail(email);
    student.setCourse(course);

    return studentRepository.save(student);
  }

  @Transactional
  public Student updateStudent(Long id, String name, String lastname, String email, ECourseStudent course) {
    Student student = getStudentById(id);
    student.setName(name);
    student.setLastname(lastname);
    student.setEmail(email);
    student.setCourse(course);
    return studentRepository.update(student);
  }

  @Transactional(readOnly = true)
  public boolean studentExists(Long id) {
    return studentRepository.existsById(id);
  }
}
