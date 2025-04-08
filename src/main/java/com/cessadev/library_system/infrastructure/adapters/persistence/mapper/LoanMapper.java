package com.cessadev.library_system.infrastructure.adapters.persistence.mapper;

import com.cessadev.library_system.domain.Loan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LoanMapper {
  List<Loan> findAll();
  void save(Loan loan);
  void update(Loan loan);
  Optional<Loan> findById(Long id);
  List<Loan> findActiveLoansByStudent(Long studentId);
  Optional<Loan> findActiveLoanByCopy(Long copyId);
  List<Loan> findOverdueLoans();
  List<Loan> findLoansByBook(Long bookId);
  int countActiveLoansByStudent(Long studentId);
}
