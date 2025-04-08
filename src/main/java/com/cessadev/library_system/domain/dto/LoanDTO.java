package com.cessadev.library_system.domain.dto;

import com.cessadev.library_system.domain.Loan;
import com.cessadev.library_system.domain.enums.ELoanStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LoanDTO {
  private Long id;
  private LocalDate loanDate;
  private LocalDate estimatedReturnDate;
  private LocalDate actualReturnDate;
  private ELoanStatus status;
  private CopyBookSimpleDTO copyBook;
  private StudentSimpleDTO student;

  public static LoanDTO fromEntity(Loan loan) {
    LoanDTO dto = new LoanDTO();
    dto.setId(loan.getId());
    dto.setLoanDate(loan.getLoanDate());
    dto.setEstimatedReturnDate(loan.getEstimatedReturnDate());
    dto.setActualReturnDate(loan.getActualReturnDate());
    dto.setStatus(loan.getStatus());
    dto.setCopyBook(CopyBookSimpleDTO.fromEntity(loan.getCopyBook()));
    dto.setStudent(StudentSimpleDTO.fromEntity(loan.getStudent()));
    return dto;
  }
}
