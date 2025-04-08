package com.cessadev.library_system.domain;

import com.cessadev.library_system.domain.enums.ELoanStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {
  private Long id;
  private LocalDate loanDate;
  private LocalDate estimatedReturnDate;
  private LocalDate actualReturnDate;
  private ELoanStatus status;
  private Long copybookId;
  private Long studentId;

  // Objetos completos para consultas específicas
  private CopyBook copyBook;
  private Student student;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Loan load = (Loan) o;
    return Objects.equals(id, load.id) &&
            Objects.equals(loanDate, load.loanDate) &&
            Objects.equals(estimatedReturnDate, load.estimatedReturnDate) &&
            Objects.equals(actualReturnDate, load.actualReturnDate) &&
            status == load.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            id,
            loanDate,
            estimatedReturnDate,
            actualReturnDate,
            status);
  }
}
