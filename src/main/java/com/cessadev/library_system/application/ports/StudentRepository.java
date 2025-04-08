package com.cessadev.library_system.application.ports;

import com.cessadev.library_system.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
  Optional<Student> findById(Long id);
  List<Student> findAll();
  Optional<Student> findByIdentification(String identification);
  Student save(Student student);
  Student update(Student student);
  boolean existsById(Long id);
}
