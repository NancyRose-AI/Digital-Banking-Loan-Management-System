package com.banking.digital.repository;
import com.banking.digital.entity.FraudLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FraudLogRepository extends JpaRepository<FraudLog, Long> {

    List<FraudLog> findByUserId(Long userId);

    List<FraudLog> findByUserIdAndResolvedOrderByCreatedAtDesc(Long userId, Boolean resolved);

    List<FraudLog> findByRiskLevel(String riskLevel);

    List<FraudLog> findByResolved(Boolean resolved);

    List<FraudLog> findTop50ByOrderByCreatedAtDesc();

    @Query("SELECT f FROM FraudLog f WHERE f.user.id = :userId AND f.eventType = :eventType AND f.createdAt >= :since")
    List<FraudLog> findRecentByUserAndType(
        @Param("userId") Long userId,
        @Param("eventType") String eventType,
        @Param("since") LocalDateTime since);

    long countByResolved(Boolean resolved);

    long countByRiskLevel(String riskLevel);
}


