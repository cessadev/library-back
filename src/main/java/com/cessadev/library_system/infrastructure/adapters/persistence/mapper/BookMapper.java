package com.cessadev.library_system.infrastructure.adapters.persistence.mapper;

import com.cessadev.library_system.domain.Book;
import com.cessadev.library_system.domain.dto.BookSimpleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {
  List<Book> findAll();
  Optional<Book> findById(Long id);
  void save(Book book);
  void update(Book book);
  Optional<Book> findByIsbn(String isbn);
  void deleteById(Long id);
  boolean existsById(Long id);
  List<Book> findByCategoryId(Long categoryId);

  @Select("SELECT category_id FROM Book WHERE id = #{bookId}")
  Long findCategoryIdByBookId(Long bookId);

}
