package com.banking.digital.repository;
import com.banking.digital.entity.KycDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KycDocumentRepository extends JpaRepository<KycDocument, Long> {
    List<KycDocument> findByUserId(Long userId);
    List<KycDocument> findByStatus(String status);
}


