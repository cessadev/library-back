package com.cessadev.library_system.infrastructure.adapters.persistence.mapper;

import com.cessadev.library_system.domain.CopyBook;
import com.cessadev.library_system.domain.enums.ECopyBookStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CopyBookMapper {
  Optional<CopyBook> findById(Long id);
  List<CopyBook> findAvailableCopiesByBookId(Long bookId);
  void updateStatus(Long id, ECopyBookStatus status);
  Optional<CopyBook> findByCode(String code);
  void save(CopyBook copyBook);
  boolean existsById(Long id);
  List<CopyBook> findByBookId(Long bookId);
  void deleteByBookId(Long bookId);
  List<CopyBook> findAvailableCopies();
  List<CopyBook> findAvailableByBookId(Long bookId);

  @Select("SELECT book_id FROM BookCopy WHERE id = #{copyId}")
  Long findBookIdByCopyId(Long copyId);
}
