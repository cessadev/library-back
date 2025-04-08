package com.cessadev.library_system.infrastructure.adapters.persistence;

import com.cessadev.library_system.application.ports.CategoryRepository;
import com.cessadev.library_system.domain.Category;
import com.cessadev.library_system.infrastructure.adapters.persistence.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

  private final CategoryMapper categoryMapper;

  public CategoryRepositoryImpl(CategoryMapper categoryMapper) {
    this.categoryMapper = categoryMapper;
  }

  @Override
  public List<Category> findAll() {
    return categoryMapper.findAll();
  }

  @Override
  public Optional<Category> findById(Long id) {
    return categoryMapper.findById(id);
  }
}
