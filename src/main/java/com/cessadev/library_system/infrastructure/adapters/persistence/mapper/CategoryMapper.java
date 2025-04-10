package com.cessadev.library_system.infrastructure.adapters.persistence.mapper;

import com.cessadev.library_system.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CategoryMapper {
  List<Category> findAll();
  Optional<Category> findById(Long id);
}
