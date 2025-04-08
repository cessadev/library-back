package com.cessadev.library_system.infrastructure.adapters.web;

import com.cessadev.library_system.application.services.LoanService;
import com.cessadev.library_system.domain.Loan;
import com.cessadev.library_system.domain.dto.CreateLoanDTO;
import com.cessadev.library_system.domain.dto.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

  private final LoanService loanService;

  public LoanController(LoanService loanService) {
    this.loanService = loanService;
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping
  public ResponseEntity<List<LoanDTO>> getAllLoans() {
    List<LoanDTO> loans = loanService.getAllLoans();
    return ResponseEntity.ok(loans);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  public ResponseEntity<LoanDTO> createLoan(@RequestBody CreateLoanDTO createLoanDTO) {
    LoanDTO loan = loanService.createLoan(createLoanDTO);

    return ResponseEntity.created(URI.create("/api/v1/load/" + loan.getId()))
            .body(loan);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PutMapping("/{id}/return")
  public ResponseEntity<LoanDTO> returnLoan(@PathVariable Long id) {
    Loan returnedLoan = loanService.returnBook(id);
    return ResponseEntity.ok(LoanDTO.fromEntity(returnedLoan));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/student/{studentId}")
  public List<LoanDTO> getActiveLoansByStudent(@PathVariable Long studentId) {
    return loanService.getActiveLoansByStudent(studentId).stream()
            .map(LoanDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/overdue")
  public List<LoanDTO> getOverdueLoans() {
    return loanService.getOverdueLoans().stream()
            .map(LoanDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/{id}")
  public ResponseEntity<LoanDTO> getLoanDetails(@PathVariable Long id) {
    Loan loan = loanService.getLoanDetails(id);
    return ResponseEntity.ok(LoanDTO.fromEntity(loan));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/book/{bookId}")
  public List<LoanDTO> getLoansByBook(@PathVariable Long bookId) {
    return loanService.getLoansByBook(bookId).stream()
            .map(LoanDTO::fromEntity)
            .collect(Collectors.toList());
  }
}
