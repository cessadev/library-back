package com.cessadev.library_system.application.ports;

import com.cessadev.library_system.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
  List<Book> findAll();
  Optional<Book> findById(Long id);
  Book save(Book book);
  Book update(Book book);
  Optional<Book> findByIsbn(String isbn);
  void deleteById(Long id);
  boolean existsById(Long id);
  List<Book> findByCategoryId(Long categoryId);
  Long findCategoryIdByBookId(Long bookId);
}
