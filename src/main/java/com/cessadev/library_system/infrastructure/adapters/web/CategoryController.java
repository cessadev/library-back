package com.cessadev.library_system.infrastructure.adapters.web;

import com.cessadev.library_system.application.services.CategoryService;
import com.cessadev.library_system.domain.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    List<CategoryDTO> categories = categoryService.getAllCategories().stream()
            .map(CategoryDTO::fromEntity)
            .collect(Collectors.toList());

    return ResponseEntity.ok(categories);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
    return categoryService.getCategoryById(id)
            .map(CategoryDTO::fromEntity)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }
}
