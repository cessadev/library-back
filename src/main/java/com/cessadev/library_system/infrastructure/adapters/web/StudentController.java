package com.cessadev.library_system.infrastructure.adapters.web;

import com.cessadev.library_system.application.services.StudentService;
import com.cessadev.library_system.domain.Student;
import com.cessadev.library_system.domain.dto.CreateStudentDTO;
import com.cessadev.library_system.domain.dto.StudentDTO;
import com.cessadev.library_system.domain.dto.UpdateStudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping
  public List<StudentDTO> getAllStudents() {
    return studentService.getAllStudents().stream()
            .map(StudentDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/{id}")
  public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
    Student student = studentService.getStudentById(id);
    return ResponseEntity.ok(StudentDTO.fromEntity(student));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/identification/{identification}")
  public ResponseEntity<StudentDTO> getStudentByIdentification(
          @PathVariable String identification) {

    Student student = studentService.getStudentByIdentification(identification);
    return ResponseEntity.ok(StudentDTO.fromEntity(student));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  public ResponseEntity<StudentDTO> createStudent(
          @RequestBody CreateStudentDTO createStudentDTO) {

    Student student = studentService.createStudent(
            createStudentDTO.getName(),
            createStudentDTO.getLastname(),
            createStudentDTO.getIdentification(),
            createStudentDTO.getEmail(),
            createStudentDTO.getCourse());

    return ResponseEntity.created(URI.create("/api/v1/student/" + student.getId()))
            .body(StudentDTO.fromEntity(student));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PutMapping("/{id}")
  public ResponseEntity<StudentDTO> updateStudent(
          @PathVariable Long id,
          @RequestBody UpdateStudentDTO updateStudentDTO) {

    Student updatedStudent = studentService.updateStudent(
            id,
            updateStudentDTO.getName(),
            updateStudentDTO.getLastname(),
            updateStudentDTO.getEmail(),
            updateStudentDTO.getCourse());

    return ResponseEntity.ok(StudentDTO.fromEntity(updatedStudent));
  }
}
