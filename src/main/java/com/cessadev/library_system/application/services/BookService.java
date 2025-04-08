package com.cessadev.library_system.application.services;

import com.cessadev.library_system.application.ports.BookRepository;
import com.cessadev.library_system.application.ports.CopyBookRepository;
import com.cessadev.library_system.domain.Book;
import com.cessadev.library_system.domain.CopyBook;
import com.cessadev.library_system.domain.enums.ECopyBookStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final CopyBookRepository copyBookRepository;

  public BookService(BookRepository bookRepository, CopyBookRepository copyBookRepository) {
    this.bookRepository = bookRepository;
    this.copyBookRepository = copyBookRepository;
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Optional<Book> getBookById(Long id) {
    return bookRepository.findById(id);
  }

  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  public Book updateBook(Book book) {
    return bookRepository.update(book);
  }

  public List<Book> getByCategoryId(Long categoryId) {
    return bookRepository.findByCategoryId(categoryId);
  }

  public Long getCategoryIdByBookId(Long bookId) {
    return bookRepository.findCategoryIdByBookId(bookId);
  }

  public Optional<Book> getBookByIsbn(String isbn) {
    return bookRepository.findByIsbn(isbn);
  }

  public void deleteBook(Long id) {
    Book book = bookRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado, id: " + id));

    List<CopyBook> copies = copyBookRepository.findByBookId(id);

    boolean allAvailable = copies.stream()
            .allMatch(copy -> copy.getStatus() == ECopyBookStatus.AVAILABLE);

    if (!allAvailable) {
      throw new IllegalStateException("No se puede ELIMINAR este libro. Por lo menos un ejemplar no se ha devuelto");
    }

    copyBookRepository.deleteByBookId(id);
    bookRepository.deleteById(book.getId());
  }
}
