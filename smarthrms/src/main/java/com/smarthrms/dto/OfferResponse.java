
package com.smarthrms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record OfferResponse(
        Long id,
        Long applicationId,
        String candidateName,
        String jobTitle,
        BigDecimal offeredSalary,
        LocalDate joiningDate,
        LocalDate validUntil,
        String status,
        LocalDateTime createdAt
) {
}
