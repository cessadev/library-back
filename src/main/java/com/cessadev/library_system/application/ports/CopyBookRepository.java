package com.cessadev.library_system.application.ports;

import com.cessadev.library_system.domain.CopyBook;
import com.cessadev.library_system.domain.enums.ECopyBookStatus;

import java.util.List;
import java.util.Optional;

public interface CopyBookRepository {
  Optional<CopyBook> findById(Long id);
  List<CopyBook> findAvailableCopiesByBookId(Long bookId);
  void updateStatus(Long id, ECopyBookStatus status);
  Optional<CopyBook> findByCode(String code);
  CopyBook save(CopyBook copyBook);
  boolean existsById(Long id);
  List<CopyBook> findByBookId(Long bookId);
  void deleteByBookId(Long bookId);
  List<CopyBook> findAvailableCopies();
  List<CopyBook> findAvailableByBookId(Long bookId);
  Long findBookIdByCopyId(Long copyId);
}
