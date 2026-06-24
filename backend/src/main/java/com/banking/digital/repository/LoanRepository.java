package com.banking.digital.repository;
import com.banking.digital.entity.Loan;
import com.banking.digital.entity.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByLoanReference(String loanReference);
    List<Loan> findByUserId(Long userId);
    List<Loan> findByStatus(LoanStatus status);
}


