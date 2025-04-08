package com.cessadev.library_system.application.ports;

import com.cessadev.library_system.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
  List<Category> findAll();
  Optional<Category> findById(Long id);
}
