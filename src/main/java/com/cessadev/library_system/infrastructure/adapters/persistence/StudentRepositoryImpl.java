package com.cessadev.library_system.infrastructure.adapters.persistence;

import com.cessadev.library_system.application.ports.StudentRepository;
import com.cessadev.library_system.domain.Student;
import com.cessadev.library_system.infrastructure.adapters.persistence.mapper.StudentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

  private final StudentMapper studentMapper;

  public StudentRepositoryImpl(StudentMapper studentMapper) {
    this.studentMapper = studentMapper;
  }

  @Override
  public Optional<Student> findById(Long id) {
    return studentMapper.findById(id);
  }

  @Override
  public List<Student> findAll() {
    return studentMapper.findAll();
  }

  @Override
  public Optional<Student> findByIdentification(String identification) {
    return studentMapper.findByIdentification(identification);
  }

  @Override
  public Student save(Student student) {
    if (student.getId() == null) {
      studentMapper.save(student);
    }
    return student;
  }

  @Override
  public Student update(Student student) {
    studentMapper.update(student);
    return student;
  }

  @Override
  public boolean existsById(Long id) {
    return studentMapper.existsById(id);
  }
}
