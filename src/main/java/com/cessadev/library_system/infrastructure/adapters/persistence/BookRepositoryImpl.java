package com.cessadev.library_system.infrastructure.adapters.persistence;

import com.cessadev.library_system.application.ports.BookRepository;
import com.cessadev.library_system.domain.Book;
import com.cessadev.library_system.infrastructure.adapters.persistence.mapper.BookMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

  private final BookMapper bookMapper;

  public BookRepositoryImpl(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
  }

  @Override
  public List<Book> findAll() {
    return bookMapper.findAll();
  }

  @Override
  public Optional<Book> findById(Long id) {
    return bookMapper.findById(id);
  }

  @Override
  public Book save(Book book) {
    if (book.getId() == null) {
      bookMapper.save(book);
    } else {
      bookMapper.update(book);
    }
    return book;
  }

  @Override
  public List<Book> findByCategoryId(Long categoryId) {
    return bookMapper.findByCategoryId(categoryId);
  }

  @Override
  public Long findCategoryIdByBookId(Long bookId) {
    return bookMapper.findCategoryIdByBookId(bookId);
  }

  @Override
  public Book update(Book book) {
    bookMapper.update(book);
    return book;
  }

  @Override
  public Optional<Book> findByIsbn(String isbn) {
    return bookMapper.findByIsbn(isbn);
  }

  @Override
  public void deleteById(Long id) {
    bookMapper.deleteById(id);
  }

  @Override
  public boolean existsById(Long id) {
    return bookMapper.existsById(id);
  }
}
