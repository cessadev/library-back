package com.cessadev.library_system.infrastructure.adapters.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/system")
public class SystemController {

  private final DataSource dataSource;

  public SystemController(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @GetMapping("/health")
  public ResponseEntity<String> healthCheck() {
    try (Connection conn = dataSource.getConnection()) {
      return ResponseEntity.ok("Successful connection to the database");
    } catch (SQLException e) {
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
              .body("Error: Failed connection to the database");
    }
  }
}
