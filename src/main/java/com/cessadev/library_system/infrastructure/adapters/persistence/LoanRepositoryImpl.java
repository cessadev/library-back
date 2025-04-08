package com.cessadev.library_system.infrastructure.adapters.persistence;

import com.cessadev.library_system.application.ports.LoanRepository;
import com.cessadev.library_system.domain.Loan;
import com.cessadev.library_system.infrastructure.adapters.persistence.mapper.LoanMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

  private final LoanMapper loadMapper;

  public LoanRepositoryImpl(LoanMapper loadMapper) {
    this.loadMapper = loadMapper;
  }

  @Override
  public List<Loan> findAll() {
    return loadMapper.findAll();
  }

  @Override
  public Loan save(Loan load) {
    if (load.getId() == null) {
      loadMapper.save(load);
    }
    return load;
  }

  @Override
  public Loan update(Loan load) {
    loadMapper.update(load);
    return load;
  }

  @Override
  public Optional<Loan> findById(Long id) {
    return loadMapper.findById(id);
  }

  @Override
  public List<Loan> findActiveLoansByStudent(Long studentId) {
    return loadMapper.findActiveLoansByStudent(studentId);
  }

  @Override
  public Optional<Loan> findActiveLoanByCopy(Long copyId) {
    return loadMapper.findActiveLoanByCopy(copyId);
  }

  @Override
  public List<Loan> findOverdueLoans() {
    return loadMapper.findOverdueLoans();
  }

  @Override
  public List<Loan> findLoansByBook(Long bookId) {
    return loadMapper.findLoansByBook(bookId);
  }

  @Override
  public int countActiveLoansByStudent(Long studentId) {
    return loadMapper.countActiveLoansByStudent(studentId);
  }
}
