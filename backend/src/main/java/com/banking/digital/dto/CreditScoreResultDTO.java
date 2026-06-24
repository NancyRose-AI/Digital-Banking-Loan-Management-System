package com.banking.digital.dto;
import com.banking.digital.entity.CreditScore;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CreditScoreResultDTO {
    private CreditScore creditScore;
    private List<String> factors;
    private String ratingCategory;
}


