package com.banking.digital.repository;
import com.banking.digital.entity.EmiSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmiScheduleRepository extends JpaRepository<EmiSchedule, Long> {
    List<EmiSchedule> findByLoanId(Long loanId);
    List<EmiSchedule> findByLoanIdAndStatus(Long loanId, String status);
    Optional<EmiSchedule> findByLoanIdAndInstallmentNumber(Long loanId, int installmentNumber);
    long countByLoanIdAndStatus(Long loanId, String status);

    /**
     * Find the earliest pending EMI across all ACTIVE loans belonging to the given user.
     * Returns all pending installments sorted by due date ascending; caller takes get(0).
     */
    @Query("SELECT e FROM EmiSchedule e " +
           "JOIN e.loan l " +
           "WHERE l.user.id = :userId " +
           "AND l.status = com.banking.digital.entity.LoanStatus.ACTIVE " +
           "AND e.status = 'PENDING' " +
           "ORDER BY e.dueDate ASC")
    List<EmiSchedule> findPendingEmisByUserId(@Param("userId") Long userId);
}


