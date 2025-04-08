package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
  private Long id;
  private String name;
  private String description;

  public CategoryDTO(Category category) {
    this.id = category.getId();
    this.name = String.valueOf(category.getName());
    this.description = category.getDescription();
  }

  public static CategoryDTO fromEntity(Category category) {
    CategoryDTO dto = new CategoryDTO();
    dto.setId(category.getId());
    dto.setName(String.valueOf(category.getName()));
    dto.setDescription(category.getDescription());
    return dto;
  }
}
