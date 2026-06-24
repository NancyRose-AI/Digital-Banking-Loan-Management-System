package com.banking.digital.repository;
import com.banking.digital.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySourceAccountIdOrDestinationAccountId(Long sourceId, Long destId);
    List<Transaction> findBySourceAccountId(Long accountId);
    List<Transaction> findByDestinationAccountId(Long accountId);
    
    @org.springframework.data.jpa.repository.Query("SELECT t FROM Transaction t LEFT JOIN t.sourceAccount sa LEFT JOIN t.destinationAccount da WHERE sa.user.id = :userId OR da.user.id = :userId ORDER BY t.createdAt DESC")
    List<Transaction> findRecentTransactionsByUserId(@org.springframework.data.repository.query.Param("userId") Long userId, org.springframework.data.domain.Pageable pageable);

    @org.springframework.data.jpa.repository.Query("SELECT t FROM Transaction t LEFT JOIN t.sourceAccount sa LEFT JOIN t.destinationAccount da WHERE sa.user.id = :userId OR da.user.id = :userId ORDER BY t.createdAt DESC")
    List<Transaction> findAllByUserId(@org.springframework.data.repository.query.Param("userId") Long userId);

    @Query("SELECT t FROM Transaction t LEFT JOIN t.sourceAccount sa LEFT JOIN t.destinationAccount da WHERE (sa.user.id = :userId OR da.user.id = :userId) AND t.createdAt >= :from AND t.createdAt <= :to ORDER BY t.createdAt DESC")
    List<Transaction> findByUserIdAndDateRange(@Param("userId") Long userId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query("SELECT COUNT(t) FROM Transaction t LEFT JOIN t.sourceAccount sa WHERE sa.user.id = :userId AND t.createdAt >= :since")
    long countRecentTransactionsByUser(@Param("userId") Long userId, @Param("since") LocalDateTime since);
}


