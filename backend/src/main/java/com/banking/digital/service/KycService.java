package com.banking.digital.service;
import com.banking.digital.entity.KycDocument;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KycService {
    KycDocument uploadDocument(Long userId, MultipartFile file, String documentType);
    List<KycDocument> getDocumentsByUser(Long userId);
    List<KycDocument> getPendingDocuments();
    void approveDocument(Long documentId);
    void rejectDocument(Long documentId);
}


