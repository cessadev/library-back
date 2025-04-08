package com.cessadev.library_system.infrastructure.adapters.persistence;

import com.cessadev.library_system.application.ports.CopyBookRepository;
import com.cessadev.library_system.domain.CopyBook;
import com.cessadev.library_system.domain.enums.ECopyBookStatus;
import com.cessadev.library_system.infrastructure.adapters.persistence.mapper.CopyBookMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CopyBookRepositoryImpl implements CopyBookRepository {

  private final CopyBookMapper copyBookMapper;

  public CopyBookRepositoryImpl(CopyBookMapper copyBookMapper) {
    this.copyBookMapper = copyBookMapper;
  }

  @Override
  public Optional<CopyBook> findById(Long id) {
    return copyBookMapper.findById(id);
  }

  @Override
  public List<CopyBook> findAvailableCopiesByBookId(Long bookId) {
    return copyBookMapper.findAvailableCopiesByBookId(bookId);
  }

  @Override
  public void updateStatus(Long id, ECopyBookStatus status) {
    copyBookMapper.updateStatus(id, status);
  }

  @Override
  public Optional<CopyBook> findByCode(String code) {
    return copyBookMapper.findByCode(code);
  }

  @Override
  public CopyBook save(CopyBook copyBook) {
    if (copyBook.getId() == null) {
      copyBookMapper.save(copyBook);
    }
    return copyBook;
  }

  @Override
  public List<CopyBook> findByBookId(Long bookId) {
    return copyBookMapper.findByBookId(bookId);
  }

  @Override
  public void deleteByBookId(Long bookId) {
    copyBookMapper.deleteByBookId(bookId);
  }

  @Override
  public List<CopyBook> findAvailableCopies() {
    return copyBookMapper.findAvailableCopies();
  }

  @Override
  public List<CopyBook> findAvailableByBookId(Long bookId) {
    return copyBookMapper.findAvailableByBookId(bookId);
  }

  @Override
  public Long findBookIdByCopyId(Long copyId) {
    return copyBookMapper.findBookIdByCopyId(copyId);
  }

  @Override
  public boolean existsById(Long id) {
    return copyBookMapper.existsById(id);
  }
}
