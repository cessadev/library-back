package com.cessadev.library_system.application.ports;

import com.cessadev.library_system.domain.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {
  List<Loan> findAll();
  Loan save(Loan load);
  Loan update(Loan load);
  Optional<Loan> findById(Long id);
  List<Loan> findActiveLoansByStudent(Long studentId);
  Optional<Loan> findActiveLoanByCopy(Long copyId);
  List<Loan> findOverdueLoans();
  List<Loan> findLoansByBook(Long bookId);
  int countActiveLoansByStudent(Long studentId);
}
