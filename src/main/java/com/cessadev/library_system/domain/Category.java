package com.cessadev.library_system.domain;

import com.cessadev.library_system.domain.enums.ECategoryName;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
  private Long id;
  private ECategoryName name;
  private String description;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return Objects.equals(id, category.id) &&
            Objects.equals(name, category.name) &&
            Objects.equals(description, category.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description);
  }
}
