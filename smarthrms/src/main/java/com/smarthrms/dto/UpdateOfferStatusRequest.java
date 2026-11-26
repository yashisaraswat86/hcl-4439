
package com.smarthrms.dto;

import com.smarthrms.enums.OfferStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOfferStatusRequest(

        @NotNull(message = "Offer status is required")
        OfferStatus status

) {
}

