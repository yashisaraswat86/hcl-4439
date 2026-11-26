
package com.smarthrms.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OfferRequest(

        @NotNull(message = "Offered salary is required")
        @DecimalMin(value = "0.0", inclusive = false,
                message = "Salary must be greater than zero")
        BigDecimal offeredSalary,

        @NotNull(message = "Joining date is required")
        @Future(message = "Joining date must be in the future")
        LocalDate joiningDate,

        @NotNull(message = "Offer validity date is required")
        @Future(message = "Offer validity date must be in the future")
        LocalDate validUntil

) {
}

