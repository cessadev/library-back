package com.cessadev.library_system.infrastructure.adapters.persistence.mapper;

import com.cessadev.library_system.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StudentMapper {
  Optional<Student> findById(Long id);
  List<Student> findAll();
  Optional<Student> findByIdentification(String identification);
  void save(Student student);
  void update(Student student);
  boolean existsById(Long id);
}
