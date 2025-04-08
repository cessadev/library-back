package com.cessadev.library_system.application.services;

import com.cessadev.library_system.application.ports.BookRepository;
import com.cessadev.library_system.application.ports.CopyBookRepository;
import com.cessadev.library_system.domain.Book;
import com.cessadev.library_system.domain.CopyBook;
import com.cessadev.library_system.domain.enums.ECopyBookStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CopyBookService {

  private final CopyBookRepository copyBookRepository;
  private final BookRepository bookRepository;

  public CopyBookService(CopyBookRepository copyBookRepository, BookRepository bookRepository) {
    this.copyBookRepository = copyBookRepository;
    this.bookRepository = bookRepository;
  }

  @Transactional(readOnly = true)
  public CopyBook getCopyById(Long id) {
    return copyBookRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ejemplar no encontrado, id: " + id));
  }

  @Transactional(readOnly = true)
  public List<CopyBook> getAvailableCopiesByBookId(Long bookId) {
    if (!bookRepository.existsById(bookId)) {
      throw new IllegalArgumentException("Libro no encontrado, id: " + bookId);
    }
    return copyBookRepository.findAvailableCopiesByBookId(bookId);
  }

  @Transactional(readOnly = true)
  public List<CopyBook> getAvailableCopies() {
    return copyBookRepository.findAvailableCopies();
  }

  @Transactional(readOnly = true)
  public List<CopyBook> getAvailableByBookId(Long bookId) {
    return copyBookRepository.findAvailableByBookId(bookId);
  }

  @Transactional(readOnly = true)
  public Long getBookIdByCopyId(Long copyId) {
    return copyBookRepository.findBookIdByCopyId(copyId);
  }

  @Transactional
  public CopyBook createCopyBook(String code, Long bookId) {
    Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado, id: " + bookId));

    CopyBook copyBook = new CopyBook();
    copyBook.setCode(code);
    copyBook.setStatus(ECopyBookStatus.AVAILABLE);
    copyBook.setBook(book);

    return copyBookRepository.save(copyBook);
  }

  @Transactional(readOnly = true)
  public CopyBook getCopyByCode(String code) {
    return copyBookRepository.findByCode(code)
            .orElseThrow(() -> new IllegalArgumentException("Ejemplar no encontrado, code: " + code));
  }

  @Transactional
  public CopyBook updateCopyStatus(Long copyId, ECopyBookStatus status) {
    CopyBook copyBook = getCopyById(copyId);
    copyBook.setStatus(status);
    copyBookRepository.updateStatus(copyId, status);
    return copyBook;
  }

  @Transactional(readOnly = true)
  public boolean isCopyAvailable(Long copyId) {
    CopyBook copy = getCopyById(copyId);
    return copy.getStatus() == ECopyBookStatus.AVAILABLE;
  }
}
