package com.cessadev.library_system.application.services;

import com.cessadev.library_system.application.ports.CategoryRepository;
import com.cessadev.library_system.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Optional<Category> getCategoryById(Long id) {
    return categoryRepository.findById(id);
  }
}
