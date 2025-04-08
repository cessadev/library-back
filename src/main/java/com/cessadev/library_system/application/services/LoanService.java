package com.cessadev.library_system.application.services;

import com.cessadev.library_system.application.ports.BookRepository;
import com.cessadev.library_system.application.ports.CopyBookRepository;
import com.cessadev.library_system.application.ports.LoanRepository;
import com.cessadev.library_system.application.ports.StudentRepository;
import com.cessadev.library_system.domain.CopyBook;
import com.cessadev.library_system.domain.Loan;
import com.cessadev.library_system.domain.Student;
import com.cessadev.library_system.domain.dto.*;
import com.cessadev.library_system.domain.enums.ECopyBookStatus;
import com.cessadev.library_system.domain.enums.ELoanStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LoanService {

  private final LoanRepository loanRepository;
  private final CopyBookRepository copyBookRepository;
  private final StudentRepository studentRepository;
  private final BookRepository bookRepository;

  public LoanService(LoanRepository loadRepository, CopyBookRepository copyBookRepository, StudentRepository studentRepository, BookRepository bookRepository) {
    this.loanRepository = loadRepository;
    this.copyBookRepository = copyBookRepository;
    this.studentRepository = studentRepository;
    this.bookRepository = bookRepository;
  }

  // Máximo de préstamos permitidos por estudiante
  private static final int MAX_LOANS_PER_STUDENT = 3;

  @Transactional(readOnly = true)
  public List<LoanDTO> getAllLoans() {
    return loanRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @Transactional
  public LoanDTO createLoan(CreateLoanDTO createLoanDTO) {
    validateBookCategoryRelation(createLoanDTO.getCategoryId(), createLoanDTO.getBookId());
    validateCopyBookRelation(createLoanDTO.getBookId(), createLoanDTO.getCopyId());

    Student student = studentRepository.findById(createLoanDTO.getStudentId())
            .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

    if (loanRepository.countActiveLoansByStudent(createLoanDTO.getStudentId()) >= MAX_LOANS_PER_STUDENT) {
      throw new IllegalStateException("El estudiante ha excedido el número máximo de préstamos permitidos");
    }

    CopyBook copy = validateCopyAvailability(createLoanDTO.getCopyId());

    Loan loan = buildLoan(createLoanDTO, student, copy);
    updateCopyStatus(copy);

    return LoanDTO.fromEntity(loanRepository.save(loan));
  }

  @Transactional
  public Loan returnBook(Long loanId) {
    Loan loan = loanRepository.findById(loanId)
            .orElseThrow(() -> new IllegalArgumentException("Registro de préstamo no encontrado"));

    if (loan.getStatus() != ELoanStatus.ACTIVE) {
      throw new IllegalStateException("El préstamo NO está activo");
    }

    loan.setActualReturnDate(LocalDate.now());
    loan.setStatus(ELoanStatus.RETURNED);

    CopyBook copy = loan.getCopyBook();
    copy.setStatus(ECopyBookStatus.AVAILABLE);
    copyBookRepository.updateStatus(copy.getId(), copy.getStatus());

    return loanRepository.update(loan);
  }

  @Transactional(readOnly = true)
  public List<Loan> getActiveLoansByStudent(Long studentId) {
    if (!studentRepository.existsById(studentId)) {
      throw new IllegalArgumentException("Estudiante no encontrado");
    }
    return loanRepository.findActiveLoansByStudent(studentId);
  }

  @Transactional(readOnly = true)
  public List<Loan> getOverdueLoans() {
    return loanRepository.findOverdueLoans();
  }

  @Transactional(readOnly = true)
  public Loan getLoanDetails(Long loanId) {
    return loanRepository.findById(loanId)
            .orElseThrow(() -> new IllegalArgumentException("Registro de préstamo NO encontrado"));
  }

  @Transactional(readOnly = true)
  public List<Loan> getLoansByBook(Long bookId) {
    if (!bookRepository.existsById(bookId)) {
      throw new IllegalArgumentException("Libro NO encontrado");
    }
    return loanRepository.findLoansByBook(bookId);
  }

  private LoanDTO convertToDTO(Loan loan) {
    Objects.requireNonNull(loan, "El préstamo no puede ser nulo");
    Objects.requireNonNull(loan.getCopyBook(), "El ejemplar no puede ser nulo");
    Objects.requireNonNull(loan.getStudent(), "El estudiante no puede ser nulo");

    LoanDTO dto = new LoanDTO();
    dto.setId(loan.getId());
    dto.setLoanDate(LocalDate.parse(loan.getLoanDate().toString()));
    dto.setEstimatedReturnDate(LocalDate.parse(loan.getEstimatedReturnDate().toString()));
    dto.setActualReturnDate(loan.getActualReturnDate() != null ?
            LocalDate.parse(loan.getActualReturnDate().toString()) : null);
    dto.setStatus(loan.getStatus());

    dto.setCopyBook(CopyBookSimpleDTO.fromEntity(loan.getCopyBook()));
    dto.setStudent(StudentSimpleDTO.fromEntity(loan.getStudent()));

    return dto;
  }

  private void validateBookCategoryRelation(Long categoryId, Long bookId) {
    Long actualCategoryId = bookRepository.findCategoryIdByBookId(bookId);
    if (!categoryId.equals(actualCategoryId)) {
      throw new IllegalArgumentException("El libro no pertenece a la categoría seleccionada");
    }
  }

  private void validateCopyBookRelation(Long bookId, Long copyId) {
    Long actualBookId = copyBookRepository.findBookIdByCopyId(copyId);
    if (!bookId.equals(actualBookId)) {
      throw new IllegalArgumentException("La copia no pertenece al libro seleccionado");
    }
  }

  private CopyBook validateCopyAvailability(Long copyId) {
    CopyBook copy = copyBookRepository.findById(copyId)
            .orElseThrow(() -> new IllegalArgumentException("Copia no encontrada"));

    if (copy.getStatus() != ECopyBookStatus.AVAILABLE) {
      throw new IllegalStateException("La copia seleccionada no está disponible");
    }

    if (loanRepository.findActiveLoanByCopy(copyId).isPresent()) {
      throw new IllegalStateException("La copia ya tiene un préstamo activo");
    }

    return copy;
  }

  private Loan buildLoan(CreateLoanDTO createLoanDTO, Student student, CopyBook copy) {
    Loan loan = new Loan();
    loan.setCopyBook(copy);
    loan.setStudent(student);
    loan.setLoanDate(LocalDate.now());
    loan.setEstimatedReturnDate(LocalDate.now().plusDays(createLoanDTO.getLoanDays()));
    loan.setStatus(ELoanStatus.ACTIVE);
    return loan;
  }

  private void updateCopyStatus(CopyBook copy) {
    copy.setStatus(ECopyBookStatus.LOANED);
    copyBookRepository.updateStatus(copy.getId(), copy.getStatus());
  }
}
